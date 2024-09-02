<#import "static/template.ftl" as t>
<@t.template>
    <h1 style="text-align: center">Random Fact</h1>
    <div class="card text-white bg-dark my-3 product">
        <img src="/res/images/cuteCate.jpeg" alt="mdo" class="card-img-top square-image">
    </div>
    <hr>
    <p style="text-align: center">${catFact.getFact()}</p>

    <hr>
    <hr>
    <p style="text-align: center">
    <#if !existInUserList>
        <div class="form-container">
            <form action="/random-cat-fact/save" method="post">
                <input type="hidden" name="fact" value="${catFact.getFact()}">
                <input type="hidden" name="length" value="${catFact.getLength()}">
                <input type="submit" value="Save" class="bottom-t-c"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            </form>
        </div>
<#--    <#else>-->
<#--        <div class="form-container">-->
<#--            <form action="/random-cat-fact/delete/{}" method="post">-->
<#--                <input type="submit" value="Save" class="bottom-t-c"/>-->
<#--                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
<#--            </form>-->
<#--        </div>-->
    </#if>
    </p>

</@t.template>