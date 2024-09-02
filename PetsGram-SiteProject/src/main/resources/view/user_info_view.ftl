<#import "static/template.ftl" as t>
<@t.template>
    <h1 style="text-align: center">User info</h1>
    <hr>
    <div style="text-align: center">
        <img src="/res/images/no-avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.getFirstName()} ${user.getLastName()}</h2>
        <br>
        Email: <b>${user.getEmail()}</b>
    </div>
    <br>
    <hr>
    <h1 style="text-align: center">User`s posts</h1>
    <br>
    <div class="card-columns">
        <#list posts as post>
            <a href="/post/${post.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">

                    <img src="/images/${post.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${post.name}</h5>
                        <p class="card-text">${post.description}</p>
                    </div>
                    <div class="card-footer">
                        ${user.getFirstName()} ${user.getLastName()}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">No posts</h1>
        </#list>
    </div>
</@t.template>