<#import "static/template.ftl" as t>
<@t.template>
    <h1 style="text-align: center">Cat Fact</h1>
    <div class="card text-white bg-dark my-3 product">
        <img src="/res/images/cuteCate.jpeg" alt="mdo" class="card-img-top square-image">
    </div>
    <hr>
    <p style="text-align: center">${catFact.getFact()}</p>

    <hr>
    <hr>
    <p style="text-align: center">

    <div class="form-container">
        <form action="/random-cat-fact/delete/${catFact.getId()}" method="post">
            <input type="submit" value="Delete" class="bottom-t-c"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </form>
    </div>

    </p>

</@t.template>

