package lesson05.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import lesson05.vo.Member;

public class MemberDao
{
//	Connection connection;
	
	// Dao의 경우 ServletContext 저장소에 접근하지 못함으로 set 메서드를 통해 주입
	// 이렇게 작업에 필요한 객체를 외부로부터 주입 받는것을 '의존성 주입 DI(Dependency Injection)/ 역제어IoC(Inversion of Control) 이라고 한다. 
	
	/* DBConnectionPool 에서 처리
	// 기본 커넥션 정보 주입
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}
	*/
	
//	DBConnectionPool connPool;
	DataSource ds;

	/*
	// DBConnectionPool 객체를 주입하기 위한 코드 준비
	public void setDbConnectionPool(DBConnectionPool connPool)
	{
		this.connPool = connPool;
	}
	*/
	
	// memberList 페이지 selectDao 처리
	public List<Member> selectList() throws Exception

	{
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select mno,mname,email,cre_date from members order by mno asc");
			ArrayList<Member> members = new ArrayList<Member>();
			
			while(rs.next())
			{
				members.add(new Member().setNo(rs.getInt("mno")).setName(rs.getString("mname")).setEmail(rs.getString("email")).setCreatedDate(rs.getDate("CRE_DATE")));
			}
			return members;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
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
				if(connection != null)
				{
//					ConnectionPool에 connection객체 반환
//					connPool.returnConnection(connection);
					connection.close();
				}
			}
			catch(Exception e)
			{
			}
		}
	}
	
	// 신규 회원 insert 처리
	public int insert(Member member) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;
		try
		{
//			connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.prepareStatement("insert into members(email,pwd,mname,cre_date,mod_date) values(?,?,?,NOW(),NOW())");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			return stmt.executeUpdate();
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
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
				if(connection != null)
				{
//					ConnectionPool에 connection객체 반환
//					connPool.returnConnection(connection);
					connection.close();
				}
			}
			catch(Exception e)
			{
			}
		}
				
	}

	// 회원 delete 처리
	public int delete(int no) throws Exception
	{
		Connection connection = null;
		Statement stmt = null;
		try
		{
//			connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.createStatement();
			return stmt.executeUpdate("DELETE FROM MEMBERS WHERE MNO=" + no);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally 
		{
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
				if(connection != null)
				{
//					ConnectionPool에 connection객체 반환
//					connPool.returnConnection(connection);
					connection.close();
				}
			}
			catch(Exception e)
			{
			}
		}
	}
	
	// 개인 회원 상세정보 select
	public Member selectOne(int no) throws Exception
	{
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
//			connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select mno,email,mname,cre_date from members where mno=" + no);
			Member member = new Member();
			
			if(rs.next()) // 실해해서 받은 정보가 있을경우
			{
				return member.setNo(rs.getInt("MNO")).setEmail(rs.getString("EMAIL")).setName(rs.getString("MNAME")).setCreatedDate(rs.getDate("CRE_DATE"));
			}
			else
			{
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
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
				if(connection != null)
				{
//					ConnectionPool에 connection객체 반환
//					connPool.returnConnection(connection);
					connection.close();
				}
			}
			catch(Exception e)
			{
			}
		}
	}
	public int update(Member member) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try
		{
//			connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.prepareStatement("UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=NOW() WHERE MNO=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			return stmt.executeUpdate();

		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
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
				if(connection != null)
				{
//					ConnectionPool에 connection객체 반환
//					connPool.returnConnection(connection);
					connection.close();
				}
			}
			catch(Exception e)
			{
			}
		}
	}
	
	public Member exist(String email, String password) throws Exception
	{
		PreparedStatement stmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try
		{
//			connection = connPool.getConnection();
			connection = ds.getConnection();
			// email과 pwd가 일치하는지 조회
			stmt = connection.prepareStatement("select mname,email from members where email=? and pwd=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			// 조회내용이 있을경우 해당 이메일과 패스워드가 일치할경우
			if(rs.next())
			{
				// vo 객체에 정보를 담는다.
				Member member = new Member().setEmail(rs.getString("email")).setName(rs.getString("mname"));
				return member;
			}
			else
			{
				return null;
			}
			

		}
		catch(Exception e)
		{
			throw e;
		}

		finally
		{
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
				if(connection != null)
				{
//					ConnectionPool에 connection객체 반환
//					connPool.returnConnection(connection);
					connection.close();
				}
			}
			catch(Exception e)
			{
			}
		}
		
	}

	public void setDataSource(DataSource ds)
	{
		this.ds = ds;
	}
	
}
