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
import lesson05.vo.Member;

@WebServlet("/member/update")
@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet
{
	@Override
	// 사용자가 클릭한 사용자의 상세정보를 출력하는 컨트롤
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
//		Connection conn = null;
		
		Member member = new Member();
		/*
		Statement stmt = null;
		ResultSet rs = null;
		*/
		try
		{
			/*
			 * 기존에 class파일에 하드코딩 해버린 방법 DriverManager.registerDriver(new
			 * com.mysql.jdbc.Driver()); conn =
			 * DriverManager.getConnection("jdbc:mysql://localhost/studydb",
			 * "study", "6453hs");
			 */
			// 클래스 로딩 class.forName()은 인자값으로 클래스 이름을 넘기 면 해당 클래스를 찾아 로딩합니다.
			/* 초기화 매개변수를 이용해 변수 로딩
			Class.forName(this.getInitParameter("driver"));
			// this.getInitParameter 메서드를 이용하여 서블릿 초기화 매개변수를 불러올수있다.
			conn = DriverManager.getConnection(this.getInitParameter("url"), this.getInitParameter("username"),
					this.getInitParameter("password"));
			*/
			// 컨텍스트 매개변수를 이용해 변수를 읽어들임
			ServletContext sc = this.getServletContext();
			/*
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),sc.getInitParameter("password"));
			*/
			
//			conn = (Connection) sc.getAttribute("conn");
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
//			memberDao.setConnection(conn);
			
			/* memberDao에서 selectOne메서드로 처리
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select mno,email,mname,cre_date from members where mno=" + req.getParameter("no"));
			*/
			
			/* 개인 방식대로 처리
			if(rs.next())
			{
				Member member = new Member();
				member.setNo(Integer.parseInt(req.getParameter("no"))).setName(rs.getString("mname")).setEmail("email").setCreatedDate(rs.getDate("cre_date"));
				req.setAttribute("member", member);
			}
			else 
			{
				throw new Exception("회원 정보를 찾을수 없습니다.");
				
			}
			*/
			
			/* memberDao에서 selectOne메서드로 처리
			if(rs.next()) // 실해해서 받은 정보가 있을경우
			{
				req.setAttribute("member", new Member().setNo(rs.getInt("MNO")).setEmail(rs.getString("EMAIL")).setName(rs.getString("MNAME")).setCreatedDate(rs.getDate("CRE_DATE")));
			}
			else
			{
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
			*/
			member = memberDao.selectOne(Integer.parseInt(req.getParameter("no")));
			req.setAttribute("member", member);
			
			RequestDispatcher rd = req.getRequestDispatcher("/member/MemberUpdateForm.jsp");
			// 해당request정보를 같이 전달.
			rd.forward(req, resp);

//			rs.next();

			/*
			// 보내는 데이터 인코딩정의
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>회원정보</title></head>");
			out.println("<body><h1>회원정보</h1>");
			out.println("<form action='update' method='post'>");
			out.println("번호: <input type='text' name='no' value='" + req.getParameter("no") + "' readonly><br>");
			out.println("이름: *<input type='text' name='name' value='" + rs.getString("mname") + "'><br>");
			out.println("이메일: <input type='text' name='email' value='" + rs.getString("email") + "'><br>");
			out.println("가입일: " + rs.getDate("cre_date") + "<br>");
			out.println("<input type ='submit' value='저장'>");
			// 삭제버튼 구현
			out.println("<input type='button' value='삭제' " + "onclick='location.href=\"delete?no="
					+ req.getParameter("no") + "\";'>");
			// onclick='location.href=경로 속성을 사용해 리다이렉트
			out.println("<input type ='button' value='취소'" + " onclick='location.href=\"list\"'>");
			out.println("</form>");
			out.println("</body></html>");
			*/
		}
		catch(Exception e)
		{
//			throw new ServletException(e);
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
		finally
		{
			/*
			// 열어둔 연결객체를 열었던 역순으로 닫아준다.
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
			/*// AppInitServlet 처리
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
			*/
		}
	}

	@Override
	// 업데이트 확인버튼을 눌러서 전달되었을경우
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/* filter로 기능구현
		// 메시지 바디에 한글과 같은 멀티바이트 문자가 있을때 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");*/
//		Connection conn = null;
		
//		PreparedStatement stmt = null;
		
		try
		{
			/*
			Class.forName(this.getInitParameter("driver"));
			conn = DriverManager.getConnection(this.getInitParameter("url"), this.getInitParameter("username"),
					this.getInitParameter("password"));
			*/
			
			/*
			// 컨텍스트 매개변수를 이용해 변수를 읽어들임
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),sc.getInitParameter("password"));
			*/
			
			/*
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			stmt = conn.prepareStatement("UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now()" + " WHERE MNO=?");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("name"));
			stmt.setInt(3, Integer.parseInt(request.getParameter("no")));
			stmt.executeUpdate();
			*/
			
			ServletContext sc = this.getServletContext();
//			conn = (Connection) sc.getAttribute("conn");
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
//			memberDao.setConnection(conn);
			Member member = new Member().setEmail(request.getParameter("email")).setName(request.getParameter("name")).setNo(Integer.parseInt(request.getParameter("no")));
			memberDao.update(member);
			
			response.sendRedirect("list");

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
				if(stmt != null)
					stmt.close();
			}
			catch(Exception e)
			{
			}
			*/
			/*// AppInitServlet 처리
			try
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception e)
			{
			}
			*/
		}
	}

}
