<#import "static/template.ftl" as t>
<@t.template>
    `<h1 style="text-align: center">Administration</h1><hr>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Email</th>
            <th scope="col">Role</th>
            <th scope="col">Delete?</th>
            <th scope="col">Edit</th>
            <th scope="col">Info</th>
        </tr>
        <#list users as user>
            <tr>
                <th>${user.getEmail()}</th>
                <th>${user.getRole()}</th>
                <th>
                    <form action="/admin/user/delete/${user.id}" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <input type="submit" class="btn btn-light" value="Delete">
                    </form>
                </th>
                <th><a href="/admin/user/edit/${user.id}">Change Role</a></th>
                <th><a href="/user/${user.id}">More info</a></th>
            </tr>
        <#else>
            <h3>No users</h3>
        </#list>
    </table>
</@t.template>