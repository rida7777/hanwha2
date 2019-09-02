<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	label{
		width:150px;
		background-color:orange;
		display:inline-block;
	}
	input{
	
	}
</style>

</head>
<body>
<h1>직원의 상세보기</h1>
<form action="empdetail" method="post">
<label>직원번호 :</label> <input type="number" disabled="disabled" name="aa" value="${emp.employee_id}"><br>
		<input type="hidden" name="employee_id" value="${emp.employee_id}">
<label>직원이름 :</label> <input type="text" name="first_name" value="${emp.first_name }"><br>
<label>직원성 :</label> <input type="text" name="last_name" value="${emp.last_name }"><br>
<label>이메일 :</label> <input type="text" name="email" value="${emp.email }"><br>
<label>전화번호 :</label> <input type="text" name="phone_number" value="${emp.phone_number }"><br>
<label>급여 :</label> <input type="number" name="salary" value="${emp.salary }"><br>
<label>입사일 :</label> <input type="date" name="hire_date" value="${emp.hire_date }"><br>
<label>매니저 :</label> 
	<select name="manager_id">
	 <c:forEach items="${managerlist }" var="m">
	 	<option ${emp.manager_id==m?"selected":"" }>${m}</option>
	 </c:forEach>
	</select>
<br>

<label>부서 :</label> 
<select name="department_id">
 <c:forEach items="${deptlist }" var="dept">
 	<c:if test="${emp.department_id == dept.department_id }">
 		<option selected value="${dept.department_id}">${dept.department_name }</option>
 	</c:if>
  	<c:if test="${emp.department_id != dept.department_id }">
 		<option value="${dept.department_id}">${dept.department_name }</option>
 	</c:if>
 </c:forEach>
</select>
<br>


<label>커미션 :</label> <input type="text" name="commission_pct" value="${emp.commission_pct }"><br>

<label>직무 :</label>
<select name="job_id">
	<c:forEach items="${joblist }" var="job">
		<option ${emp.job_id==job ? "selected":""}>${job}</option>
	</c:forEach>
</select>
<br>

<input type="submit" value="수정하기">
</form>

</body>
</html>