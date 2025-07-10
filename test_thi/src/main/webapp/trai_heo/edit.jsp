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
  <title>Chỉnh sửa và Chi tiết Heo</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
  <style>
    .card-container {
      max-width: 600px;
      margin: 2rem auto;
    }
    .form-label {
      font-weight: 600;
      margin-bottom: 0.25rem;
    }
    .form-control, .btn {
      border-radius: 0.375rem;
    }
    .btn-group {
      margin-top: 1rem;
      display: flex;
      gap: 0.5rem;
    }
  </style>
</head>
<body>
<div class="card-container">
  <div class="card shadow-sm">
    <div class="card-header bg-primary text-white">
      <h3 class="card-title mb-0">Chi tiết Heo</h3>
    </div>
    <div class="card-body">
      <form method="post" action="/traiheo?action=update">
        <input type="hidden" name="id" value="${traiHeoList.id}">

        <div class="mb-2">
          <label class="form-label">Mã Heo</label>
          <input class="form-control form-control-sm" type="text" name="ma_so_heo" value="${traiHeoList.maSoHeo}" required>
        </div>

        <div class="mb-2">
          <label class="form-label">Ngày Nhập</label>
          <input class="form-control form-control-sm" type="text" name="ngay_nhap_chuong" value="${traiHeoList.ngayNhapChuong}" required>
        </div>

        <div class="mb-2">
          <label class="form-label">Trọng Lượng Nhập</label>
          <input class="form-control form-control-sm" type="text" name="trong_luong_nhap_chuong" value="${traiHeoList.trongLuongNhapChuong}">
        </div>

        <div class="mb-2">
          <label class="form-label">Ngày Xuất</label>
          <input class="form-control form-control-sm" type="text" name="ngay_xuat_chuong" value="${traiHeoList.ngayXuatChuong}">
        </div>

        <div class="mb-2">
          <label class="form-label">Trọng Lượng Xuất</label>
          <input class="form-control form-control-sm" type="text" name="trong_luong_xuat_chuong" value="${traiHeoList.trongLuongXuatChuong}">
        </div>

        <div class="mb-2">
          <label class="form-label">Danh mục</label>
          <select class="form-control form-control-sm" name="id_xuat_xu">
            <c:forEach items="${xuatXuList}" var="xuatxu">
              <option value="${xuatxu.idXuatXu}"
                      <c:if test="${xuatxu.idXuatXu == traiHeoList.idXuatXu}">selected</c:if>>
                  ${xuatxu.tenXuatXu}
              </option>
            </c:forEach>
          </select>
        </div>

        <div class="btn-group">
          <button class="btn btn-primary btn-sm" type="submit">Lưu</button>
          <a href="/traiheo" class="btn btn-secondary btn-sm">Hủy</a>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>