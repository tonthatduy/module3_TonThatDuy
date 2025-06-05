<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 04/06/2025
  Time: 3:19 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Product</title>
</head>
<body>
<h1>Product details</h1>
<p>
    <a href="/products">Back to product list</a>
</p>
<table>
    <tr>
        <td>Name: </td>
        <td>${requestScope["product"].getName()}</td>
    </tr>
    <tr>
        <td>Price: </td>
        <td>${requestScope["product"].getPrice()}</td>
    </tr>
    <tr>
        <td>Description: </td>
        <td>${requestScope["product"].getDescription()}</td>
    </tr>
    <tr>
        <td>Publisher: </td>
        <td>${requestScope["product"].getPublisher()}</td>
    </tr>
    <tr>
        <td>Category: </td>
        <td>${requestScope["product"].getIdCategory()}</td>
    </tr>
</table>
</body>
</html>
