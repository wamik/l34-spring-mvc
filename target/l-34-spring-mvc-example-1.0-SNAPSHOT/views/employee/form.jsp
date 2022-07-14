<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${modeTitle} Employee</title>
</head>
<body>

<h1>${mode} Employee</h1>

<form action="<%=request.getContextPath()%>/employee"
      method="post"
      enctype="multipart/form-data"
>
    <input name="id" type="hidden" value="${employee.id}">

    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="${employee.name}"/></td>
        </tr>
        <tr>
            <td>Department:</td>
            <td><input type="text" name="department" value="${employee.department}"/></td>
        </tr>

        <tr>
            <td>Choose image to upload (PNG, JPEG)</td>
            <td><input type="file" name="image" accept="image/png, image/jpeg"></td>
        </tr>

    </table>

    <c:if test="${isUpdate}">
        <img src="<%=request.getContextPath()%>/employee/image/${employee.id}" width="30%">
    </c:if>

    <input type="submit" value="Save">
</form>

</body>
</html>
