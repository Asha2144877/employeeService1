<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>show top level managers with hyperlink to their subordinates page</title>
</head>
    <body>

<div align = "center"> 

    Subordinates<br>
    <table border="1">
    <thead>
        <tr>
            
            <td>Employee Code</td>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Emailid</td>
            <td>PhoneNumber</td>
            <td>Designation</td>
            <td>Address</td>
            <td>Manager ID</td>
            <td>Subordinate Count</td>
            <td>Actions</td>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="employee" items="${subordinates}">
            <tr>
                <td>
                    <c:choose>
  <c:when test="${employee.subordinateCount != 0}">
    <a href =  "/api/v1/employees/showSubordinates?id=${employee.id}"> ${employee.employeeCode}</a>
  </c:when>
  <c:otherwise>
    ${employee.employeeCode}
  </c:otherwise>
</c:choose>
                
         </td>        
                <td> ${employee.firstName} </td>
               <td> ${employee.lastName} </td>
               <td> ${employee.emailId} </td>
                <td> ${employee.phoneNumber} </td>
                <td> ${employee.designation} </td>
                <td> ${employee.address} </td>
                <td> ${employee.manager.employeeCode} </td>
                <td> ${employee.subordinateCount} </td>
                <td>
                    <a href =  "/api/v1/employees/editEmployee?id=${employee.id}"> Edit</a>
                    <c:if test="${employee.subordinateCount == 0}">
                            <a href =  "/api/v1/employees/deleteEmployee?id=${employee.id}"> Delete</a>
                    </c:if>                    
                  
                </td>
            <br>
            </tr>
        </c:forEach>
         </tbody>
</table>
    </div>  
        
    <form method = "get" action = "/api/v1/employees/backToHierarchyLandingPage"> 
        <input type="submit" value= "back">
    </form>    
    </body>
</html>