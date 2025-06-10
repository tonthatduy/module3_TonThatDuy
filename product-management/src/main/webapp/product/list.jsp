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
        <link rel="stylesheet" href="bootstrap520/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="datatables/css/dataTables.bootstrap5.min.css"/>
        <script src="bootstrap520/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
    <h1>Products</h1>
    <p>
        <a href="/products?action=create">Create new product</a>
    </p>
    <%--<form method="get" action="/products" class="mb-3">--%>
    <%--    <input type="hidden" name="action" value="search">--%>
    <%--    <div class="input-group mb-2">--%>
    <%--        <input type="text" class="form-control" name="searchName" placeholder="Nhập tên sản phẩm"--%>
    <%--               value="${param.searchName}">--%>
    <%--        <input type="text" class="form-control" name="searchCategory" placeholder="Nhập tên danh mục"--%>
    <%--               value="${param.searchCategory}">--%>
    <%--        <select name="id_category" id="id_category" class="form-select">--%>
    <%--            <option value="">------Chọn Thể Loại------</option>--%>
    <%--            <c:forEach items="${categories}" var="category">--%>
    <%--                <option value="${category.idCategory}"--%>
    <%--                        <c:if test="${param.id_category == category.idCategory}">selected</c:if>>--%>
    <%--                        ${category.nameCategory}--%>
    <%--                </option>--%>
    <%--            </c:forEach>--%>
    <%--        </select>--%>
    <%--        <button class="btn btn-primary" type="submit">Tìm kiếm</button>--%>
    <%--    </div>--%>
    <%--</form>--%>
    <form method="get" action="/products" class="mb-3">
        <input type="hidden" name="action" value="search">
        <div class="input-group">
            <!-- Ô nhập tên sản phẩm -->
            <input type="text" class="form-control" name="searchName" placeholder="Nhập tên sản phẩm"
                   value="${param.searchName}">

            <!-- Dropdown chọn thể loại -->
            <select name="searchCategory" id="id_category" class="form-select">
                <option value="">------Chọn Thể Loại------</option>
                <c:forEach items="${categories}" var="category">
                    <option value="${category.idCategory}"
                            <c:if test="${param.searchCategory == category.idCategory}">selected</c:if>>
                            ${category.nameCategory}
                    </option>
                </c:forEach>
            </select>

            <!-- Nút tìm kiếm -->
            <button class="btn btn-primary" type="submit">Tìm kiếm</button>
        </div>
    </form>

    <form action="/products" method="get" style="display:inline-block; margin-left: 10px;">
        <input class="btn btn-outline-primary" type="submit" value="Hiển thị tất cả">
    </form>
    <table id="tableProduct" class="table table-striped table-bordered" style="width:100%">
        <thead>
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
        </thead>
        <tbody>
        <c:forEach items='${requestScope["products"]}' var="product">
            <tr>
                <td><a class="text-decoration-none text-info"
                       href="/products?action=view&id=${product.getId()}">${product.getName()}</a></td>
                <td>${product.getName()}</td>
                <td>${product.getPrice()}</td>
                <td>${product.getDescription()}</td>
                <td>${product.getPublisher()}</td>
                <td>${product.getNameCategory()}</td>
                <td>
                    <a href="/products?action=edit&id=${product.id}" class="btn btn-warning btn-sm">Edit</a>
                </td>
                <td>
                    <button class="btn btn-danger btn-sm delete-btn"
                            data-id="${product.id}"
                            data-name="${product.name}"
                            data-bs-toggle="modal"
                            data-bs-target="#exampleModal">Delete
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-center mt-4">
        <c:forEach var="i" begin="1" end="${totalPage}">
            <a href="/products?page=${i}"
               class="${i == currentPage ? 'btn btn-primary' : 'btn btn-outline-primary'} m-1">${i}</a>
        </c:forEach>
    </div>
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
    <script src="jquery/jquery-3.5.1.min.js"></script>
    <script src="datatables/js/jquery.dataTables.min.js"></script>
    <script src="datatables/js/dataTables.bootstrap5.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#tableProduct').dataTable({
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
                    "emptyTable": "Không có sản phẩm nào",
                    "search": "Tìm kiếm:"
                }
            });
        });

        // JS
        $(document).on("click", ".delete-btn", function () {
            const id = $(this).data("id");
            const name = $(this).data("name");
            $("#deleteId").val(id);
            $("#deleteName").text(name);
        });

    </script>
    </body>
    </html>
