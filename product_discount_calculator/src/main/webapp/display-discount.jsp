<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 03/06/2025
  Time: 3:00 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Product Discount Calculator</h1>
<form action="/display_discount" method="post">
  <input type="text" name="description" placeholder="Enter description: ">
  <input type="number" name="list_price" placeholder="Enter List Price: ">
  <input type="number" name="discount" placeholder="Enter Discount Percent: ">
  <input type="submit" id="submit" value="calculator">
</form>
</body>
</html>
