<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 08/06/2025
  Time: 11:07 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="datatables/css/dataTables.bootstrap5.min.css"/>
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .btn-primary {
            background: linear-gradient(45deg, #007bff, #00d4ff);
            border: none;
        }
        .btn-primary:hover {
            background: linear-gradient(45deg, #0056b3, #0098cc);
        }
        .btn-danger {
            background: linear-gradient(45deg, #dc3545, #ff6b6b);
            border: none;
        }
        .btn-danger:hover {
            background: linear-gradient(45deg, #a71d2a, #cc5252);
        }
        .search-form .form-select, .search-form .form-control {
            border-radius: 20px;
        }
        .dataTables_wrapper .dataTables_paginate .paginate_button {
            border-radius: 50%;
            margin: 0 5px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="card p-4">
        <h2 class="mb-4"><i class="bi bi-book"></i> Student List</h2>

        <div class="d-flex justify-content-between align-items-center mb-4">
            <a href="/products?action=create" class="btn btn-primary"><i class="bi bi-plus-circle"></i> Create New Student</a>

            <form class="d-flex search-form" action="/products" method="get">
                <input type="text" name="search" class="form-control me-2" placeholder="Nhập tên học sinh">
                <select name="class" class="form-select me-2" style="width: 150px;">
                    <option value="">All Classes</option>
                    <c:forEach items="${classList}" var="classItem">
                        <option value="${classItem}">${classItem}</option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn btn-outline-primary"><i class="bi bi-search"></i> Tìm kiếm</button>
            </form>

            <a href="/products" class="btn btn-secondary"><i class="bi bi-list-ul"></i> Hiển thị tất cả</a>
        </div>

        <table id="tableStudent" class="table table-bordered table-hover text-center">
            <thead class="table-light">
            <tr>
                <th>Name</th>
                <th>Class</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${studentlist}" var="student">
                <tr>
                    <td>
                        <a href="/products?action=view&id=${student.id}" class="text-decoration-none fw-bold text-primary">
                                ${student.name}
                        </a>
                    </td>
                    <td>${student.nameClass}</td>
                    <td>
                        <a href="/products?action=edit&id=${student.id}" class="btn btn-warning btn-sm"><i class="bi bi-pencil"></i> Edit</a>
                    </td>
                    <td>
                        <button class="btn btn-danger btn-sm delete-btn"
                                data-id="${student.id}"
                                data-name="${student.name}"
                                data-bs-toggle="modal"
                                data-bs-target="#deleteModal">
                            <i class="bi bi-trash"></i> Delete
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Delete Confirmation Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Confirm Delete</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete student <strong id="deleteName"></strong>?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <form action="/products?action=delete" method="post">
                    <input type="hidden" name="id" id="deleteId">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    $(document).ready(function () {
        $('#tableStudent').DataTable({
            "dom": 'rtp',
            "lengthChange": false,
            "pageLength": 5,
            "language": {
                "paginate": {
                    "previous": "Trước",
                    "next": "Sau"
                },
                "info": "",
                "zeroRecords": "Không có dữ liệu",
                "emptyTable": "Không có học sinh nào",
                "search": "Tìm kiếm:"
            }
        });
    });

    $(document).on("click", ".delete-btn", function () {
        const id = $(this).data("id");
        const name = $(this).data("name");
        $("#deleteId").val(id);
        $("#deleteName").text(name);
    });
</script>
</body>
</html>