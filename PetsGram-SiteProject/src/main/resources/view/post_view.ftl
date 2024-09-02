<#import "static/template.ftl" as t>
<@t.template>
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
</@t.template>