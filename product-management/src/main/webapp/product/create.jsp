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
    <title>Create new product</title>
    <style>
        .message {
            color: green;
        }
    </style>
</head>
<body>
<h1>Create new products</h1>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/products">Back to product list</a>
</p>
<form method="post" action="products?action=create">
    <fieldset>
        <legend>Product information</legend>
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text" name="price" id="price"></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><input type="text" name="description" id="description"></td>
            </tr>
            <tr>
                <td>Publisher:</td>
                <td><input type="text" name="publisher" id="publisher"></td>
            </tr>
            <tr>
                <td>Category:</td>
                <td>
                    <select name="id_category" id="id_category">
                        <span> value="">------Chọn Thể Loại------</span>
                        <c:forEach items="${categorys}" var="category">
                            <option value="${category.idCategory}">${category.nameCategory}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create product"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
