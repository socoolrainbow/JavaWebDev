<%@page import="lesson05.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="member" class="lesson05.vo.Member" scope="session"></jsp:useBean>
<%
	// 자바 유즈빈으로 로딩
	// Member member = (Member) session.getAttribute("member");
%>
<div style="background-color: #00008b; color: #ffffff; height: 20px; padding: 5px;">
	SPMS(Simple Project Management System)
	<% if(member.getEmail() != null) {%>
	<span style="float: right;">
	<a style="color: white;"href="<%=request.getContextPath()%>/logout"><%= member.getName() %> 로그아웃</a>
	</span>
	<% } %>
</div>