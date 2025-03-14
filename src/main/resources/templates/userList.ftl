<html>
<head>
    <title>User information</title>
</head>
<body>
<h1>All users:</h1>
<ul>
<#list users as user>
  <li>
        <p>Id: ${user.id}</p>
        <p>First name: ${user.firstname}</p>
        <p>Second name: ${user.secondName}</p>
        <p>Age: ${user.age}</p>
        <p>Telephone number: ${user.telephoneNumber}</p>
        <p>Sex: ${user.sex}</p>
        <p>Created: ${user.created}</p>
        <p>Updated: ${user.updated}</p>
        <p>Deleted: ${user.deleted}</p>
  </li>
  <hr/>
</#list>
</ul>

</body>
</html>