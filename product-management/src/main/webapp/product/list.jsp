<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 04/06/2025
  Time: 3:18 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h1>Products</h1>
<p>
    <a href="/products?action=create">Create new customer</a>
</p>
<form action="/products" method="get">
    <input type="hidden" name="action" value="search">
    <input type="text" name="query" placeholder="Search by product name">
    <input type="submit" value="Search">
</form>
<form action="/products" method="get" style="display:inline-block; margin-left: 10px;">
    <input type="submit" value="Hiển thị tất cả">
</form>
<table border="1">
    <tr>
        <td>Name Details</td>
        <td>Name</td>
        <td>Price</td>
        <td>Description</td>
        <td>Publisher</td>
        <td>Category</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items='${requestScope["products"]}' var="product">
        <tr>
            <td><a href="/products?action=view&id=${product.getId()}">${product.getName()}</a></td>
            <td>${product.getName()}</td>
            <td>${product.getPrice()}</td>
            <td>${product.getDescription()}</td>
            <td>${product.getPublisher()}</td>
            <td>${product.getNameCategory()}</td>
            <td><a href="/products?action=edit&id=${product.getId()}">edit</a></td>
            <td><a href="/products?action=delete&id=${product.getId()}">delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
