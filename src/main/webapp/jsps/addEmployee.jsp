<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
 <head>
    <title>Page to add new employee</title> 
</head>   
    
<body>    
Please enter following details:<br>
<form method="post" action="/api/v1/employees/submitEmployee">
Employee Code <input type = "text" name="employeeCode" ><br>   
FirstName <input type = "text" name="firstName"  ><br>
LastName <input type = "text" name="lastName" ><br>
Phone Number <input type = "text" name="phoneNumber"  ><br>
Designation <input type = "text" name="designation" ><br>
Email <input type = "text" name="email"  ><br>
Address <input type = "text" name="address" ><br>
Manager   <select name="selectedmanager">
    <option value="0">   Not Applicable
    </option>
  <c:forEach items="${allEmployees}" var="employee" varStatus="loop">
    <option value="${employee.id}">
        ${employee.employeeCode}
    </option>
  </c:forEach>
</select> 
  <input type="submit" value="Submit Employee"/> 
</form>  
</body>    
 </html> 