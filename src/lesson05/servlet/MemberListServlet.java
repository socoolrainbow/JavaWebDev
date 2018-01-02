package lesson05.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson05.dao.MemberDao;


//http://localhost:8880/javaWebDev/member/list
@SuppressWarnings("serial")
//@WebServlet("/member/list.do")
public class MemberListServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		{
			try
			{
				ServletContext sc = this.getServletContext();
				MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
				request.setAttribute("members", memberDao.selectList());
				request.setAttribute("viewUrl", "/member/MemberList.jsp");
			}
			catch(Exception e)
			{
				throw new ServletException(e);
			}
		}
	}
}