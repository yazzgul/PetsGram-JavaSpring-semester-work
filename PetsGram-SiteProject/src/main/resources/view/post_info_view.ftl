<#import "static/template.ftl" as t>
<@t.template>
    <div class="card text-white bg-dark my-3 product">
        <img src="/images/${post.previewImageId}" alt="mdo" class="card-img-top square-image">
    </div>
    <hr>
    <p><h1 style="text-align: center">${post.getName()}</h1></p>
    <p style="text-align: center">${post.getDescription()}</p>

    <hr>
    <p style="text-align: center">
        <a href="/user/${post.getUser().getId()}">
            <img src="/res/images/no-avatar.png" alt="mdo" width="32" height="32" class="rounded-circle"> ${post.getUser().getFirstName()}
        </a>
    </p>
    <hr>
    <p style="text-align: center">
        <#if currentUserPostBoolean>
            <div class="form-container">
                <form action="/post/delete/${post.getId()}" method="post">
                    <input type="submit" value="Delete" class="bottom-t-c"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                </form>
            </div>
        </#if>
    </p>

</@t.template>

