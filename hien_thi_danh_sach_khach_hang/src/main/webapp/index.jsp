<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1> Danh sách khách hàng</h1>
<table>
    <tr>
        <th>Tên</th>
        <th>Ngày Sinh</th>
        <th>Địa Chỉ</th>
        <th>Ảnh</th>
    </tr>
    <c:forEach var="item" items="${customers}" varStatus="idx">
        <tr>
            <td>${idx.count}</td>
            <td>${item.name}</td>
            <td>${item.dayOfBirth}</td>
            <td>${item.address}</td>
            <td><img src="${item.img}" width="100"></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>