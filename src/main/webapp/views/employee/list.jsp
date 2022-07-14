<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Second page</title>
</head>
<body>

<h1> List of all employees</h1>

<a href="<%=request.getContextPath()%>/employee/new">Add</a>

<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Department</td>
    </tr>

    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.id}</td>
            <td>
                <a href="<%=request.getContextPath()%>/employee/${employee.id}">${employee.name} </a>
            </td>
            <td>${employee.department}</td>
            <td>
                <a href="<%=request.getContextPath()%>/employee/delete/${employee.id}">Удалить </a>
            </td>

        </tr>
    </c:forEach>
</table>

</body>
</html>
