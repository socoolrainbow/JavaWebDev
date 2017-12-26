package lesson05.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lesson05.dao.MemberDao;
import lesson05.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// jsp에서 다시 서블릿으로 돌아올 필요가 없어서 인클루딩 대신 포워딩으로 처리 리다이렉트
		// 로그인 성공시 /member/list페이지로
		// 로그인 실패시 /auth/LogInFrom.jsp로 포워딩
//		RequestDispatcher rd = req.getRequestDispatcher("/LogInForm.jsp");
		req.setAttribute("viewUrl", "/LogInForm.jsp");
//		rd.forward(req, resp);
	}
	
	@Override
	// 사용자가 이메일 암호를 입력한후 post요청을 하면 doPost가 호출됨
	// 만약 이메일과 암호가 일치하는 회원을 찾는다면 값 객체 Member에 회원 저보를 담는다.
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
//		Connection conn = null;
		/*
		PreparedStatement stmt = null;
		ResultSet rs = null;
		*/
		try
		{
			/*
			// email과 pwd가 일치하는지 조회
			stmt = conn.prepareStatement("select mname,email from members where email=? and pwd=?");
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("password"));
			rs = stmt.executeQuery();
			// 조회내용이 있을경우 해당 이메일과 패스워드가 일치할경우
			if(rs.next())
			{
				// vo 객체에 정보를 담는다.
				Member member = new Member().setEmail(rs.getString("email")).setName(rs.getString("mname"));
				HttpSession session = req.getSession();
				session.setAttribute("member", member);
				// list페이지로 리다이렉션
				resp.sendRedirect("member/list");
			}
			else 
			{
				RequestDispatcher rd = req.getRequestDispatcher("/LogInFail.jsp");
				rd.forward(req, resp);
			}
			*/
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			Member member = memberDao.exist(req.getParameter("email"), req.getParameter("password"));
			
			if(member != null)
			{
				HttpSession session = req.getSession();
				session.setAttribute("member", member);
				// list페이지로 리다이렉션
				req.setAttribute("viewUrl", "redirect:/member/list.do");
//				resp.sendRedirect("member/list");
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("/LogInFail.jsp");
				req.setAttribute("viewUrl","/LogInFail.jsp");
//				rd.forward(req, resp);
			}
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
		finally 
		{
			/*
			try
			{
				if(rs != null)
				{
					rs.close();
				}
			}
			catch(Exception e)
			{
			}
			try
			{
				if(stmt != null)
				{
					stmt.close();
				}
			}
			catch(Exception e)
			{
			}
			*/
		}
	}
}
