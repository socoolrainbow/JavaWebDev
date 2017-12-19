<%-- <!-- JSP 전용태그 지시자(Directive) 속성에 따라 특별한 자바코드를 생성한다. 
	jsp지시자에는 page, taglib, include가 있다.
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!-- JSP 전용태그 스크립틀릿(Scriptlet) -->
<% // 자바코드 스크립틀릿 %>
<%
String v1 ="";
String v2 ="";
String result = "";
String[] selected = {"","","",""};

// 값이 있을 때만 꺼낸다.
if(request.getParameter("v1") != null)
{
	v1 = request.getParameter("v1");
	v2 = request.getParameter("v2");
	String op = request.getParameter("op");
	
	result = calculate(Integer.parseInt(v1), Integer.parseInt(v2),op);
	if("+".equals(op))
	{
		selected[0] = "selected";
	} else if ("-".equals(op)) {
		selected[1] = "selected";
	
	} else if ("*".equals(op)){
		selected[2] = "selected";
	
	} else if ("/".equals(op)){
		selected[3] = "selected";
}
%>

<!-- HTML 형식대로 표현해주면 된다 템플릿 데이터 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>계산기</title>
</head>
<body>
<h2> JSP 계산기</h2>

<!-- form 태그를 이용해 자신인 jsp파일을 get방식으로 요청한다. -->
<form action="Calculator.jsp" method="get">

<!-- Expression 표현식으로 java구간에 선언한 변수를 불러올수 있다. -->
	<input type="text" name="v1" size="4" value="<%=v1%>">
	<select name="op">
		<option value="+" <%=selected[0] %>>+</option>
		<option value="-" <%=selected[1] %>>-</option>
		<option value="*" <%=selected[2] %>>*</option>
		<option value="/" <%=selected[3] %>>/</option>
	</select>
	<input type="text" name="v2" size="4" value="<%=v2%>">
	<input type="submit" value="=">
	<input type="text" size="8" value="<%=result%>"><br>
</form>
</body>
</html>

<!--  JSP 전용태그 선언문 (Declaration)
	아래의 선언문은 위 아래 가운데 어디든 생성해도 상관이 없다.
	해당부분은 jspService메서드 안에 복사되는 것이 아니라 jspService 밖의 클래스 블록 안에 복사된다.
 -->

<%! 
private String calculate(int a, int b, String op) {
	int r = 0;
	
	if ("+".equals(op)) {
		r = a + b;	
	} else if ("-".equals(op)) {
		r = a - b;
	} else if ("*".equals(op)) {
		r = a * b;
	} else if ("/".equals(op)) {
		r = a / b;
	}
	
	return Integer.toString(r);
}
%>  끝부분 선엄문 에러관련 확인필요 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String v1 = "";
String v2 = "";
String result = "";
String[] selected = {"", "", "", ""};

//값이 있을 때만 꺼낸다.
if (request.getParameter("v1") != null) {
	v1 = request.getParameter("v1");
	v2 = request.getParameter("v2");
	String op = request.getParameter("op");
	
	result = calculate(
				Integer.parseInt(v1), 
				Integer.parseInt(v2), 
				op);
	
	if ("+".equals(op)) {
		selected[0] = "selected";
	} else if ("-".equals(op)) {
		selected[1] = "selected";
	} else if ("*".equals(op)) {
		selected[2] = "selected";
	} else if ("/".equals(op)) {
		selected[3] = "selected";
	}
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>계산기</title>
</head>
<body>
	<h2>JSP 계산기</h2>
	<form action="Calculator.jsp" method="get">
		<input type="text" name="v1" size="4" value="<%=v1%>"> <select
			name="op">
			<option value="+" <%=selected[0]%>>+</option>
			<option value="-" <%=selected[1]%>>-</option>
			<option value="*" <%=selected[2]%>>*</option>
			<option value="/" <%=selected[3]%>>/</option>
		</select> <input type="text" name="v2" size="4" value="<%=v2%>"> <input
			type="submit" value="="> <input type="text" size="8"
			value="<%=result%>"><br>
	</form>
</body>
</html>
<%! 
private String calculate(int a, int b, String op) {
	int r = 0;
	
	if ("+".equals(op)) {
		r = a + b;	
	} else if ("-".equals(op)) {
		r = a - b;
	} else if ("*".equals(op)) {
		r = a * b;
	} else if ("/".equals(op)) {
		r = a / b;
	}
	
	return Integer.toString(r);
}
%>
