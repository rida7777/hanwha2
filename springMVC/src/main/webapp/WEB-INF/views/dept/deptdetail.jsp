<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>부서의 상세보기</h1>
<form action="deptdetail" method="post">
부서번호 : <input type="number" name="department_id" value="${dept.department_id }">
부서이름 : <input type="text" name="department_name" value="${dept.department_name }"><br>
DB에 저장된 이미지 : <br>
<img alt="이미지" src="${path}/resources/${dept.fileName }" width="200" height="100"><br>
직접이미지 접근 : <br>
<img alt="이미지" src="${path}/resources/photo12.jpg" width="200" height="100"><br>
<a href="deptdownload?folder=resources&file=${dept.fileName }">이미지다운받기</a>
<input type="submit" value="수정하기">
</form>
</body>
</html>