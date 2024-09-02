<#import "static/template.ftl" as t>
<@t.template>
    <div class="card-columns">
        <#list catFacts as catFact>
            <a href="/random-cat-fact/${catFact.getId()}" class="post-link">
                <div class="card text-white bg-dark my-3 product">
                    <img src="/res/images/cuteCate.jpeg" alt="mdo" class="card-img-top square-image">
                    <div class="card-body">
                        <h3 class="card-title">${catFact.getFact()}</h3>
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">No facts</h1>
        </#list>
    </div>
</@t.template>