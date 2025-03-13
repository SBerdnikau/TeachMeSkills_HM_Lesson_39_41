<#import "macros/productInfoMacro.ftl" as um>
<html>
<head>
    <title>Product information</title>
</head>
<body>
<h1>Product information:</h1>
<@um.productInfo product/>

<form action="/product" method="get">
    <input type="hidden" id="productId" name="productId" value="${product.id}" />
    <button type="submit" class="btn">Update product</button>
</form>
<form action="/product/delete" method="post">
    <input type="hidden" id="productId" name="productId" value="${product.id}" />
    <button type="submit" class="btn">Delete product</button>
</form>
</body>
</html>