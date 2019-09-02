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

<h1>직원 등록</h1>
<form action="empinsert" method="post">
<label>직원번호 :</label> <input type="number" name="employee_id"><br>
<label>직원이름 :</label> <input type="text" name="first_name"><br>
<label>직원성 :</label> <input type="text" name="last_name"><br>
<label>이메일 :</label> <input type="text" name="email" placeholder="aa@bb.cc"><br>
<label>전화번호 :</label> <input type="tel" name="phone_number" placeholder="000-0000-0000" pattern="\d{3}-\d{4}-\d{4}"><br>
<label>급여 :</label> <input type="number" name="salary" ><br>
<label>입사일 :</label> <input type="date" name="hire_date" ><br>
<label>매니저 :</label> 
<select name="manager_id">
	<c:forEach items="${managerlist}" var="m">
	  <!-- 보여주는 것과 실제 DB에 들어가는것 보두 job - id(코드) -->
	  <option>${m}</option> 
	</c:forEach>
</select>
<br>

<label>부서 :</label> 
<select name="department_id">
	<c:forEach items="${deptlist }" var="dept">
	  <!-- 보여주는 것은 부서 이름, 실제로 DB에 들어가는 것은 부서id(코드) -->
	  <option value="${dept.department_id }">${dept.department_name }</option> 
	</c:forEach>
</select>
<br>
<label>커미션 :</label> <input type="text" name="commission_pct" ><br>
<label>직무 :</label> 
<select name="job_id">
	<c:forEach items="${joblist }" var="job">
	  <!-- 보여주는 것과 실제 DB에 들어가는것 보두 job - id(코드) -->
	  <option>${job}</option> 
	</c:forEach>
</select>
<br>
<input type="submit" value="입력하기">
</form>

</body>
</html>