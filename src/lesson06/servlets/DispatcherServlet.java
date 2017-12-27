package lesson06.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson05.vo.Member;
import lesson06.controller.Controller;
import lesson06.controller.MemberListController;


@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html; charset=UTF-8");
		String servletPath = request.getServletPath();
		try
		{
			ServletContext sc = this.getServletContext();

			// map 자료구조를 사용하여 model을 선언하고 ServletContext에 저장된 memberDao를 불러와
			// 담는다.
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("memberDao", sc.getAttribute("memberDao"));

			String pageControllerPath = null;
			// 회원 목록을 처리할 페이지 컨트롤러 선언.
			Controller pageController = null;

			if("/member/list.do".equals(servletPath))
			{
				// pageControllerPath = "/member/list";
				pageController = new MemberListController();
			}
			else if("/member/add.do".equals(servletPath))
			{
				pageControllerPath = "/member/add";
				if(request.getParameter("email") != null)
				{
					request.setAttribute("member", new Member().setEmail(request.getParameter("email"))
							.setPassword(request.getParameter("password")).setName(request.getParameter("name")));
				}
			}
			else if("/member/update.do".equals(servletPath))
			{
				pageControllerPath = "/member/update";
				if(request.getParameter("email") != null)
				{
					request.setAttribute("member", new Member().setNo(Integer.parseInt(request.getParameter("no")))
							.setEmail(request.getParameter("email")).setName(request.getParameter("name")));
				}
			}
			else if("/member/delete.do".equals(servletPath))
			{
				pageControllerPath = "/member/delete";

			}
			else if("/login.do".equals(servletPath))
			{
				pageControllerPath = "/login";

			}
			else if("/logout.do".equals(servletPath))
			{
				pageControllerPath = "/logout";
			}

			// 페이지 컨트롤러의 실행
			String viewUrl = pageController.execute(model);
			// Map 객체에 저장된 값을 ServletRequest에 모두 복사.
			for(String key : model.keySet())
			{
				request.setAttribute(key, model.get(key));
			}
			if(viewUrl.startsWith("redirect:"))
			{
				response.sendRedirect(viewUrl.substring(9));
				return;
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
			/*
			 * RequestDispatcher rd =
			 * request.getRequestDispatcher(pageControllerPath);
			 * rd.include(request, response);
			 * 
			 * String viewUrl = (String) request.getAttribute("viewUrl"); //
			 * viewUrl이 redirect이면 sendRedirect를 호출
			 * if(viewUrl.startsWith("redirect:")) {
			 * response.sendRedirect(viewUrl.substring(9)); return; } else { rd
			 * = request.getRequestDispatcher(viewUrl); rd.include(request,
			 * response); }
			 */
		}
		/*
		 * 서블릿을 만들때마다 오류처리를 해줬는데, 프런트 컨트롤러에서 오류 처리를 담당하기 때문에 페이지 컨트롤러를 작성할 때는 오류
		 * 처리 코드를 넣을 필요가 없다.
		 */
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}
