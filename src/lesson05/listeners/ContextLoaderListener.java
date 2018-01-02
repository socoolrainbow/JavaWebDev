package lesson05.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lesson06.context.ApplicationContext;

@WebListener
public class ContextLoaderListener implements ServletContextListener
{
	static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}
	
	public void contextInitialized(ServletContextEvent event)
	{
		try
		{
			/*
			ServletContext sc = event.getServletContext();
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/studydb");
//			MemberDao memberDao = new MemberDao();
			MySqlMemberDao memberDao = new MySqlMemberDao();
			memberDao.setDataSource(ds);
			sc.setAttribute("/login.do", new LogInController().setMemberDao(memberDao));
			sc.setAttribute("/logout.do", new LogOutController().setMemberDao(memberDao));
			sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));
			sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(memberDao));
			sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberDao(memberDao));
			sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberDao(memberDao));
			*/
			
			ServletContext sc = event.getServletContext();
			String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
			// 프론트 컨트롤러에서 사용할 수 있게 contextLoaderLstener의 클래스 변수 ApplicationContext에 저장된다.
			applicationContext = new ApplicationContext(propertiesPath);
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
	}

}
