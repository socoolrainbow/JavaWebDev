<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="member" class="lesson05.vo.Member" scope="request"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보</title>
</head>
<body>
<h1>회원정보</h1>
<form action='update' method='post'>
		<%-- 번호: <input type='text' name='no' value='<%= member.getNo() %>' readonly><br> --%>
		<!-- Member 리퀘스트 저장객체는 useBean으로 불러왔고, 해당 member객체를 EL로 표현한다. -->
		번호: <input type='text' name='no' value='${member.no }' readonly><br>
		이름: *<input type='text' name='name' value='${member.name }'><br>
		이메일: <input type='text' name='email'	value='${member.email }'><br> 
		가입일: ${member.createdDate}<br> 
		<input type='submit' value='저장'> 
		<input type='button' value='삭제' onclick='location.href="delete?no=${member.no}"'>
		<input type='button' value='취소' onclick='location.href="list"'>
</form>
</body>
</html>
<!-- 
// 보내는 데이터 인코딩정의 resp.setContentType("text/html; charset=UTF-8");
PrintWriter out = resp.getWriter(); out.println("
<html>
<head>
<title>회원정보</title>
</head>
"); out.println("
<body>
	<h1>회원정보</h1>
	"); out.println("
	<form action='update' method='post'>
		"); out.println("번호: <input type='text' name='no'
			value='" + req.getParameter("no") + "' readonly><br>");
		out.println("이름: *<input type='text' name='name'
			value='" + rs.getString("mname") + "'><br>");
		out.println("이메일: <input type='text' name='email'
			value='" + rs.getString("email") + "'><br>");
		out.println("가입일: " + rs.getDate("cre_date") + "<br>");
		out.println("<input type='submit' value='저장'>"); // 삭제버튼 구현
		out.println("<input type='button' value='삭제'
			" + "onclick='location.href=\"delete?no="
					+ req.getParameter("no") + "\";'>");
		// onclick='location.href=경로 속성을 사용해 리다이렉트 out.println("<input
			type='button' value='취소' " + " onclick='location.href=\"list\"'>");
		out.println("
	</form>
	"); out.println("
</body>
</html>
");
 -->