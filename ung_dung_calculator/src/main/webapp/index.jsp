<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="/calculator" method="post">
    <h1>Simple Calculator</h1>
    <span>Calculator</span>
    <span>First operand:</span>
    <input type="number" name="first">
    <span>Operator:</span>
    <select name="operator">
        <option value="cong">+</option>
        <option value="tru">-</option>
        <option value="nhan">*</option>
        <option value="chia">/</option>
    </select>
    <span> Second operand:</span>
    <input type="number" name="second">
    <button type="submit"> Calculate</button>


</form>
</body>
</html>