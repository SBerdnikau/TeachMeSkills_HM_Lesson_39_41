<#import "macros/productInfoMacro.ftl" as um>
<html>
<head>
    <title>Update product</title>
</head>
<body>
<h1>Current product info:</h1>
<@um.productInfo product/>

<h1>Update user:</h1>
<form action="/product" method="post">
    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${product.name}" required>
    </div>
    <div>
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" value="${product.name}" required>
    </div>
    <div>
        <input type="hidden" id="id" name="id" value="${product.id}" />
        <button type="submit">Save changes</button>
    </div>
</form>
${product}
</body>
</html>