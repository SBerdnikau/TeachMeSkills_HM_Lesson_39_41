<#import "macros/userInfoMacro.ftl" as um>
<html>
<head>
    <title>Update user</title>
</head>
<body>
<h1>Current user info:</h1>
<@um.userInfo user/>

<h1>Update user:</h1>
<form action="/user" method="post">
    <div>
        <label for="firstname">First name:</label>
        <input type="text" id="firstname" name="firstname" value="${user.firstName}" required>
    </div>
    <div>
        <label for="secondName">Second name:</label>
        <input type="text" id="secondName" name="secondName" value="${user.secondName}" required>
    </div>
    <div>
        <label for="age">Age:</label>
        <input type="number" id="age" name="age" value="${user.age}" required>
    </div>
    <div>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}" required>
    </div>
    <div>
        <label for="gender">Gender:</label>
        <input type="text" id="gender" name="gender" value="${user.gender}" required>
    </div>
    <div>
        <label for="telephoneNumber">Telephone number:</label>
        <input type="text" id="telephoneNumber" name="telephoneNumber" value="${user.telephoneNumber}" required>
    </div>
    <div>
        <input type="hidden" id="id" name="id" value="${user.id}" />
        <button type="submit">Save changes</button>
    </div>
</form>
${user}
</body>
</html>