package lesson05.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/logout")
public class LogOutServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		// HttpSession 객체를 무효화 하기위해 invalidate를 호출. 
		// 세션 객체가 무효화 된다는 것은 HttpSession 객체가 제거된다는 것을 의미한
		session.invalidate();
		req.setAttribute("viewUrl", "redirect:/login.do");
//		resp.sendRedirect("login");
		
	}
}
