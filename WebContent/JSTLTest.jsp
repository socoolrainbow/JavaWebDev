<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- JSTL을 사용하려면 taglib 지시자를 선언해줘야한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:out value="안녕하세요!"></c:out>
<c:out value="${null}"> 반갑습니다.</c:out>
<c:out value="안녕하세요!">반갑습니다.</c:out>
<c:out value="${null}"></c:out>
<br>


</body>
</html>