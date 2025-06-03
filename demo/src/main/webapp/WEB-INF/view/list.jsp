<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 30/05/2025
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách học sinh</title>
</head>
<%--    jsp:include => nạp 1 jsp khác vào jsp hiện tại--%>
<body>
<table>
    <tr>
        <th>No</th>
        <th>Họ và tên</th>
        <th>Địa chỉ</th>
        <th>Điểm</th>
        <th>Lớp</th>
    </tr>
    <c:forEach var="item" items="${students}" varStatus="idx">
        <tr>
            <td>${idx.count}</td>
            <td>${item.name}</td>
            <td>${item.address}</td>
            <td>${item.point}</td>
            <td>${item.className}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
