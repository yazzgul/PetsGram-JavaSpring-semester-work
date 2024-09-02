<#import "static/template.ftl" as t>
<@t.template>
    <h1 style="text-align: center">Sign up</h1>
    <hr>
    <form action="/sign-up" method="post">
<#--        <div class="form-group">-->
<#--            <label for="firstName">First name</label>-->
<#--            <input type="text" class="form-control" name="firstName">-->
<#--        </div>-->
<#--        <div class="form-group">-->
<#--            <label for="lastName">Last name</label>-->
<#--            <input type="text" class="form-control" name="lastName">-->
<#--        </div>-->
        <div class="form-group">
            <label for="firstName">First name</label>
            <input type="text" class="form-control" required id="name" name="firstName">
        </div>
        <div class="form-group">
            <label for="lastName">Last name</label>
            <input type="text" class="form-control" required id="name" name="lastName">
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" required name="email">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" required name="password">
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Sign up!</button>
    </form>
    <hr>
    <h2>Have an account? <a href="/sign-in">Sign in!</a></h2>
</@t.template>