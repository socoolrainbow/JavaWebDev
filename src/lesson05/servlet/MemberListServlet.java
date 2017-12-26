package lesson05.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson05.dao.MemberDao;
//http://localhost:8880/javaWebDev/member/list
@SuppressWarnings("serial")
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException
	{
		{
//			Connection conn = null;
			/*
			Statement stmt = null;
			ResultSet rs = null;
			*/
			try {
				ServletContext sc = this.getServletContext();
				/*
				conn = (Connection) sc.getAttribute("conn"); 
				MemberDao memberDao = new MemberDao();
				*/
				// memberDao 객체에 바록 
//				memberDao.setConnection(conn);
				
				// request 저장객체에 members vo객체를 담으면서 selectList메서드를 실행한다.
				MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
				request.setAttribute("members", memberDao.selectList());
				request.setAttribute("viewUrl", "/member/MemberList.jsp");
//				response.setContentType("text/html; charset=UTF-8");
				
				/*
				stmt = conn.createStatement();
				rs = stmt.executeQuery(
						"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
						" FROM MEMBERS" +
						" ORDER BY MNO ASC");
				
				response.setContentType("text/html; charset=UTF-8");
				ArrayList<Member> members = new ArrayList<Member>();
				
				// 데이터베이스에서 회원 정보를 가져와 Member에 담는다.
				// 그리고 Member객체를 ArrayList에 추가한다.
				while(rs.next()) {
					members.add(new Member()
								.setNo(rs.getInt("MNO"))
								.setName(rs.getString("MNAME"))
								.setEmail(rs.getString("EMAIL"))
								.setCreatedDate(rs.getDate("CRE_DATE"))	);
				}
				
				// request에 회원 목록 데이터 보관한다.
				request.setAttribute("members", members);
				*/
				/* 프런트 서블릿에서 처리
				// JSP로 출력을 위임한다.
				RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
				rd.include(request, response);
				*/
			} catch (Exception e) {
				/* 프런트 서블릿에서 처리
				e.printStackTrace();
				request.setAttribute("error", e);
				RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
				rd.forward(request, response);
				*/
				throw new ServletException(e);
			} 
			/*
			finally 
			{
				//try {if (rs != null) rs.close();} catch(Exception e) {}
				//try {if (stmt != null) stmt.close();} catch(Exception e) {}
				try {if (conn != null) conn.close();} catch(Exception e) {}
			}
			*/
		}
	}
}
	/*
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost/studydb", "study", "6453hs");
			
			
			//컨텍스트 매개변수를 이용해 변수를 읽어들임
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),sc.getInitParameter("password"));
			 
			
			// AppInitServlet 서블릿 컨텍스트에 저장된 커넥션 객체를 불러온다.
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			stmt = conn.createStatement();
			// member테이블 데이터를 mno컬럼기준으로 오름차순정렬 조회
			rs = stmt.executeQuery("select mno,mname,email,cre_date from members order by mno asc");
			// 응답 리턴시 해당컨텐트 타입을 설정
			resp.setContentType("text/html; charset=UTF-8");
			
			// arrylist member vo객체를 생성한다.
			ArrayList<Member> members = new ArrayList<Member>();
			
			// 데이터베이스에서 회원 정보를 가져와 Member에 담는다.
			// 그리고 Member 객체를 arraylist에 추가한다.
			while(rs.next())
			{
				members.add(new Member().setNo(rs.getInt("mno")).setName(rs.getString("mname")).setEmail(rs.getString("email")).setCreatedDate(rs.getDate("cre_date")));
			}
			// request에 회원 목록 데이터를 보관
			req.setAttribute("members", members);
			
			// JSP로 출력을 위임한다.
			RequestDispatcher rd = req.getRequestDispatcher("/member/MemberList.jsp");
			
			
			 forward 로 위임하면 해당 서블릿으로 제어권이 넘아간후 다시 돌아오지 않는다.
			 include 위임을 하면 해당 서블릿으로 제어권을 넘긴 후 그 서블릿이 작업을 끝내면 다시 제어권이 넘어온다.
			 
//			rd.include(req, resp);
			rd.forward(req, resp);
			 해당 뷰 부분은 JSP뷰로 출력을 위임
			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>회원목록</title></head>");
			out.println("<body><h1>회원목록</h1>");
			// 신규회원 입력 서블릿 호출
			out.println("<p><a href='add'>신규 회원</a></p>");
			while(rs.next())
			{
				out.println(rs.getInt("mno") + "," + "<a href='update?no=" + rs.getInt("mno") + "'>"
						+ rs.getString("mname") + "</a>," + rs.getString("email") + "," + rs.getDate("CRE_DATE")
						+ "<a href='delete?no=" + rs.getInt("MNO") + "'>[삭제]</a><br>");
			}
			out.println("</body></html>");
			
		}
		catch(Exception e)
		{
			// try블록에 예외가 발생하면 그예외를 servletException 객체에 담아서 이 메서드를 호출한 서블릿
			// 컨테이너에 던지도록 한다.서블릿 컨테이너는 예외에 따른 적절한 화면을 생성하여 클라이언트에게 출력함.
//			throw new ServletException(e);
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
		 AppInitServlet 서블릿의 destroy메서드에서 대신 처리
		finally
		{
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
		}
		
	}
	*/
