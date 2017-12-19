package lesson05.servlet;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AppInitServlet extends HttpServlet
{
	/**
	 * 해당 부분을 ContextLoaderListener에서 처리함.
	 */
	private static final long serialVersionUID = -6318391232694745397L;

	@Override
	// 서블릿 초기화 매서드에 DB커넥션 정보를 입력해준다.
	public void init() throws ServletException
	{
		System.out.println("AppInitServlet 준비...");
		super.init();
		
		try
		{
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			Connection conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
			// 커넥션 객체를 ServletContext개제에 정장
			sc.setAttribute("conn", conn);
		}
		catch(Throwable e)
		{
			throw new ServletException(e);
		}
	}
	
	@Override
	// 종료시 해당 커넥션을 닫아준다.
	public void destroy()
	{
		System.out.println("AppInitServlet 마무리...");
		super.destroy();
		Connection conn = (Connection) this.getServletContext().getAttribute("conn");
		try
		{
			if(conn != null && conn.isClosed() == false)
			{
				conn.close();
			}
		}
		catch(Exception e)
		{
		}
	}
	
}
