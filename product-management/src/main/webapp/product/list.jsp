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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
</head>
<body>
<h1>Products</h1>
<p>
    <a href="/products?action=create">Create new customer</a>
</p>
<form method="get" action="/products" class="mb-3">
    <input type="hidden" name="action" value="search">
    <div class="input-group mb-2">
        <input type="text" class="form-control" name="searchName" placeholder="Nhập tên sản phẩm">
        <input type="text" class="form-control" name="searchCategory" placeholder="Nhập tên danh mục">
        <button class="btn btn-primary" type="submit">Tìm kiếm</button>
    </div>
</form>
<form action="/products" method="get" style="display:inline-block; margin-left: 10px;">
    <input class="btn btn-outline-primary" type="submit" value="Hiển thị tất cả">
</form>
<table class="table table-striped table-light">
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
            <td>
                <a href="/products?action=edit&id=${product.id}" class="btn btn-warning btn-sm">Edit</a>
            </td>
            <td>
                <button onclick="deleteInfo('${product.id}','${product.name}')" type="button"
                        class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    Delete
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form method="post" action="/products?action=delete">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input hidden="hidden" id="deleteId" name="deleteId">
                    <span>Bạn có muốn xoá sản phẩm </span><span id="deleteName"></span> không?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                    <button class="btn btn-primary">Xoá</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function deleteInfo(id, name) {
        document.getElementById("deleteId").value = id;
        document.getElementById("deleteName").innerText = name;
    }
</script>
</body>
</html>
