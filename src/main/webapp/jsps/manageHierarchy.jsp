<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Page to add new employee</title>
</head>
    <body>
<form method="post" action="/api/v1/employees/submitEmployee">
Employee Code <input type = "text" name="employeeCode"><br>   
FirstName <input type = "text" name="firstName"employeeCode><br>
LastName <input type = "text" name="lastName"><br>
Phone Number <input type = "text" name="phoneNumber"><br>
Designation <input type = "text" name="designation"><br>
Email <input type = "text" name="email"><br>
Address <input type = "text" name="address"><br>
 Manager   <select name="selectedmanager">
    <option value="0">   Not Applicable
    </option>
  <c:forEach items="${allEmployees}" var="employee" varStatus="loop">
    <option value="${employee.id}">
        ${employee.employeeCode}
    </option>
  </c:forEach>
</select>  
  <input type="submit" value="AddEmployee"/> 
</form> 
<div align = "center"> 

    Employees<br>
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
            <td>Manager Id</td>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="employee" items="${allEmployees}">
            <tr>
              <td> ${employee.employeeCode} </td>
                <td> ${employee.firstName} </td>
               <td> ${employee.lastName} </td>
               <td> ${employee.emailId} </td>
                <td> ${employee.phoneNumber} </td>
                <td> ${employee.designation} </td>
                <td> ${employee.address} </td>
                <td> ${employee.manager.employeeCode} </td>
            <br>
            </tr>
        </c:forEach>
       
    </tbody>
</table>
    </div>  
        
    <form method = "get" action = "/api/v1/employees/backToHierarchyLandingPage"> 
        <input type="submit" value= "Back">
    </form>    
    </body>
</html>