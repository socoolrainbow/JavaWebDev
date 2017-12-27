package lesson05.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson05.dao.MemberDao;
import lesson05.vo.Member;


@SuppressWarnings("serial")
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet
{
	@Override
	// 회원 들록화면 처리 doGet
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		/* MemberAdd.jsp로 위임
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html; charset=UTF-8");
		out.println("<html><head><title>회원 등록</title></head>");
		out.println("<body><h1>회원 등록</h1>");
		out.println("<form action='add' method='post'>");
		out.println("이름: <input type='text' name='name'><br>");
		out.println("이메일: <input type='text' name='email'><br>");
		out.println("암호: <input type='password' name='password'><br>");
		out.println("<input type='submit' value='추가'");
		out.println("<input type='reset' value='취소'");
		out.println("</form>");
		out.println("</body></html>");
		*/
		req.setAttribute("viewUrl", "/member/MemberAdd.jsp");
		
//		RequestDispatcher rd = req.getRequestDispatcher("/member/MemberAdd.jsp");
//		rd.forward(req, resp);
//		rd.include(req, resp);
	}

	// 회원등록화면에서 회원등록시 처리되는 doPost
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		/*filter로 기능 구현
		// 메시지 바디에 한글과 같은 멀티바이트 문자가 있을때 한글 깨짐 방지
		req.setCharacterEncoding("UTF-8");*/
		/*
		Connection conn = null;
		PreparedStatement stmt = null;
        */
		try
		{
			/*
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost/studydb", "study", "6453hs");
			*/
			
			/*// 컨텍스트 매개변수를 이용해 변수를 읽어들임
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),sc.getInitParameter("password"));
			*/
			ServletContext sc = this.getServletContext();
//			conn = (Connection) sc.getAttribute("conn");
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			
//			memberDao.setConnection(conn);
			
			// req에서 받아온 회원정보대로 맴버 객체를 생성
//			Member member = new Member().setEmail(req.getParameter("email")).setPassword(req.getParameter("password")).setName(req.getParameter("name"));
			// 프론트 컨트롤러에서 대신처리하여 등록되어있는 member객체를 불러호출한다.
			Member member = (Member) req.getAttribute("member");
			memberDao.insert(member);
			/*
			stmt = conn.prepareStatement(
					"insert into members(email,pwd,mname,cre_date,mod_date) values(?,?,?,NOW(),NOW())");
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("password"));
			stmt.setString(3, req.getParameter("name"));
			// stmt에 쿼리를 만들어서 update실행한다.
			stmt.executeUpdate();
			*/
			// 업데이트 실행후 바로 회원정보 리스트 조회 페이지로 리다이렉트 시킨다.
//			resp.sendRedirect("list");
			//리다이렉트는 클라이언트로 본문을 출력하지 않기 때문에 html을 출력하는 코드는 모두 주석처리함.
/*
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>회원등록결과</title>");
			// meta tag를 이용하여 리프레쉬, meta 테그는 헤더 안에 넣어줘야 동작한다.
			out.println("<meta http-equiv='Refresh' content='2; url=list'>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>등록 성공입니다.</p><br>");
			out.println("<p><a href=list>회원 조회</a></p>");
			out.println("</body></html>");*/
			
/*			// 리프레쉬 정보를 응답 헤더에 추가하여 페이지 로딩후 3초뒤에 list페이지로 이동
			resp.addHeader("Refresh", "3;url=list");*/
			
			req.setAttribute("viewUrl", "redirect:list.do");

		}
		catch(Exception e)
		{
			throw new ServletException(e);
			/*
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
			*/
		}
		/*
		finally
		{
			// 열어둔 연결객체를 열었던 역순으로 닫아준다.
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
			
			try
			{
				if(conn != null)
				{
					conn.close();
				}
			}
			catch(Exception e)
			{

			}
			
		}*/
		
	}

}
