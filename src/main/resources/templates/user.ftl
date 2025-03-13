<#import "macros/userInfoMacro.ftl" as um> 
<html>
<head>
    <title>User information</title>
</head>
<body>
<h1>User information:</h1>
<@um.userInfo user/>

<form action="/user" method="get">
    <input type="hidden" id="userId" name="userId" value="${user.id}" />
    <button type="submit" class="btn">Update user</button>
</form>
<form action="/user/delete" method="post">
    <input type="hidden" id="userId" name="userId" value="${user.id}" />
    <button type="submit" class="btn">Delete user</button>
</form>
</body>
</html>