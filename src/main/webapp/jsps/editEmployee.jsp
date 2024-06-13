<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
 <head>
    <title>Page to edit existing employee details</title> 
</head>
 <body>   
    Please enter following details:<br>
<form method="post" action="/api/v1/employees/submitEdittedEmployee">
Employee Code <input type = "text" name="employeeCode" value="${employeeToEdit.employeeCode}"><br>   
FirstName <input type = "text" name="firstName" value="${employeeToEdit.firstName}" ><br>
LastName <input type = "text" name="lastName"  value="${employeeToEdit.lastName}"><br>
Phone Number <input type = "text" name="phoneNumber" value="${employeeToEdit.phoneNumber}" ><br>
Designation <input type = "text" name="designation" value="${employeeToEdit.designation}"><br>
Email <input type = "text" name="email"  value="${employeeToEdit.emailId}"><br>
Address <input type = "text" name="address"  value="${employeeToEdit.address}"><br>
Manager   <select name="selectedmanager" value= "${employeeToEdit.manager.id}">
    <option value="0">   Not Applicable
    </option>
  <c:forEach items="${allEmployees}" var="employee" varStatus="loop">
    <option value="${employee.id}">
        ${employee.employeeCode}
    </option>
  </c:forEach>
</select> 
  <input type="submit" value="Update Employee"/> 
</form>  
    
<form method = "get" action = "/api/v1/employees/backToHierarchyLandingPage"> 
        <input type="submit" value= "Back">
    </form> 

</body>    
 </html>