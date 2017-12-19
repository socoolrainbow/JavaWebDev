package lesson05.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson05.dao.MemberDao;


@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
//		Connection conn = null;
//		Statement stmt = null;

		try
		{
			/*
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),sc.getInitParameter("password"));
			*/
			// AppInitServlet 서블릿에서 생성한 커넥션정보를 서블릿컨텍스트 저장소에서 불러옴
			
			// mameberDao에서 delete 처리
			ServletContext sc = this.getServletContext();
//			conn = (Connection) sc.getAttribute("conn");
			MemberDao memberDao =  (MemberDao) sc.getAttribute("memberDao");
			
//			memberDao.setConnection(conn);
			memberDao.delete(Integer.parseInt(request.getParameter("no")));
			
			/*
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM MEMBERS WHERE MNO=" + request.getParameter("no"));
			 */
			response.sendRedirect("list");

		}
		catch(Exception e)
		{
//			throw new ServletException(e);
			e.printStackTrace();
			// 생성된 에러를 e객체에 담고 해당 객체를 request로 등록.
			request.setAttribute("error", e);
			// requestdispatcher 로 리다이렉션
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			// 자원을 위이하여 다시돌아오지 않는 포워드
			rd.forward(request, response);

		}
		/* AppInitServlet 서블릿에서 처리
		finally
		{
			try
			{
				if(stmt != null)
					stmt.close();
			}
			catch(Exception e)
			{
			}
			try
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception e)
			{
			}
		}
		*/
	}
}
