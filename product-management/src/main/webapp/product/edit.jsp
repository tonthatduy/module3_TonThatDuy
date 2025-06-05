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
    <title>Chỉnh sửa sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<h1>Chỉnh sửa sản phẩm</h1>
<form method="post" action="/products?action=update">
    <input type="hidden" name="id" value="${product.id}">

    <div class="mb-3">
        <label class="form-label">Tên sản phẩm</label>
        <input class="form-control" type="text" name="name" value="${product.name}" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Giá</label>
        <input class="form-control" type="number" name="price" value="${product.price}" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Mô tả</label>
        <input class="form-control" type="text" name="description" value="${product.description}">
    </div>

    <div class="mb-3">
        <label class="form-label">Nhà sản xuất</label>
        <input class="form-control" type="text" name="publisher" value="${product.publisher}">
    </div>

    <div class="mb-3">
        <label class="form-label">Danh mục</label>
        <select class="form-control" name="id_category">
            <c:forEach items="${categoryList}" var="cat">
                <option value="${cat.idCategory}"
                        <c:if test="${cat.idCategory == product.idCategory}">selected</c:if>>
                        ${cat.nameCategory}
                </option>
            </c:forEach>
        </select>
    </div>

    <button class="btn btn-primary" type="submit">Cập nhật</button>
    <a href="/products" class="btn btn-secondary">Quay lại</a>
</form>
</body>
</html>