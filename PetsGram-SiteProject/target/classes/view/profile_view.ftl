<#import "static/template.ftl" as t>
<@t.template>
    <h1 style="text-align: center">Profile</h1>
    <hr>
    <br>
    <div style="text-align: center">
        <img src="/res/images/no-avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.firstName} ${user.lastName}</h2>
        <br>
        Email: <b>${user.email}</b>
        <br>
        <br>
        <#if user.isAdmin()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Administration
            </a><br>
        </#if>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
           href="/profile/random-cat-fact">
            Saved cats facts
        </a><br>

        <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
           data-toggle="collapse" href="#collapseExample" role="button"
           aria-expanded="true" aria-controls="collapseExample">
            Add new post!
        </a>
        <div class="collapse mb-3" id="collapseExample" style="">
            <form action="/post/create" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="postName">Your pet name</label>
                    <input type="text" required class="form-control"
                           id="postName" name="name">
                </div>
                <div class="form-group">
                    <label for="description">Add description</label>
                    <textarea required class="form-control" id="description" name="description"></textarea>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input required type="file" name="file1" id="file1">
                        <label class="custom-file-label" for="file1">Choose photo</label>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button type="submit" style="width: 100%" class="btn btn-dark">Add</button>
            </form>
        </div>
        <hr>
        <br>
        My posts:
        <br>
        <div class="card-columns">
            <#list posts as post>
                <a href="/post/${post.id}" class="post-link">
                    <div class="card text-white bg-dark my-3 product">

                        <img src="/images/${post.previewImageId}"
                             class="card-img-top">
                        <div class="card-body">
                            <h5 class="card-title">${post.getName()}</h5>
                            <p class="card-text">${post.getDescription()}</p>
                        </div>
                        <div class="card-footer">
                            ${post.getUser().firstName} ${post.getUser().lastName}
                        </div>
                    </div>
                </a>
            <#else>
                <h1 style="text-align: center">No posts</h1>
            </#list>
        </div>
        <form action="/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Sign out
            </button>
        </form>
    </div>
</@t.template>



