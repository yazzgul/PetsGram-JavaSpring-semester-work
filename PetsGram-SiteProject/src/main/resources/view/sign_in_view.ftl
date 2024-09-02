<#import "static/template.ftl" as t>
<@t.template>
<h1 style="text-align: center">Sign in</h1>
<hr>
<form action="/sign-in" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <div class="form-group">
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" required name="email">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" required name="password">
        </div>
<#--        <div class="form-group">-->
<#--            <label for="email">Email</label>-->
<#--            <input type="text" name="email" placeholder="email" value="admin@admin.com">-->
<#--        </div>-->
<#--        <div class="form-group">-->
<#--            <label for="password">Password</label>-->
<#--            <input type="password" name="password" placeholder="password" value="123456">-->
<#--        </div>-->
    </div>
    <button type="submit" style="width: 100%" class="btn btn-dark">Go</button>
</form>
<hr>
<h2>Don`t have an account? <a href="/sign-up">Sign up!</a></h2>
</@t.template>