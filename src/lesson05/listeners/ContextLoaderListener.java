package lesson05.listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import lesson05.dao.MemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener
{
//	Connection conn;
//	DBConnectionPool connPool;
//	BasicDataSource ds;

	public void contextInitialized(ServletContextEvent event)
	{
		/*
		// memberDao를 ServletConext에 정장한다.
		try
		{
			ServletContext sc = event.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),sc.getInitParameter("password"));
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			sc.setAttribute("memberDao", memberDao);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		*/
		try
		{
			ServletContext sc = event.getServletContext();
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/studydb");
//			Class.forName(sc.getInitParameter("driver"));
//			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),sc.getInitParameter("password"));
			/*
			ds = new BasicDataSource();
			ds.setDriverClassName(sc.getInitParameter("driver"));
			ds.setUrl(sc.getInitParameter("url"));
			ds.setUsername(sc.getInitParameter("username"));
			ds.setPassword(sc.getInitParameter("password"));
			*/
//			connPool = new DBConnectionPool(sc.getInitParameter("driver"), sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
//			memberDao.setDbConnectionPool(connPool);
			memberDao.setDataSource(ds);
			sc.setAttribute("memberDao", memberDao);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public ContextLoaderListener()
	{
	}

	public void contextDestroyed(ServletContextEvent sce)
	{
		/*
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
		}
		*/
//		connPool.closeAll();
		/*
		try
		{
			if(ds != null)
			{
				ds.close();
			}
			
		}
		catch(Exception e)
		{
		}
		 */
	}

}
