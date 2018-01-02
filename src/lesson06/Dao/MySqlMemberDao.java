package lesson06.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import lesson05.vo.Member;
import lesson06.inter.MemberDao;

public class MySqlMemberDao implements MemberDao
{
	DataSource ds;
	
	@Override
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
				members.add(new Member()
						.setNo(rs.getInt("mno"))
						.setName(rs.getString("mname"))
						.setEmail(rs.getString("email"))
						.setCreatedDate(rs.getDate("CRE_DATE")));
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
					connection.close();
				}
			}
			catch(Exception e)
			{
			}
		}
	}

	@Override
	public int insert(Member member) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;
		try
		{
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
					connection.close();
				}
			}
			catch(Exception e)
			{
			}
		}
	}

	@Override
	public int delete(int no) throws Exception
	{
		Connection connection = null;
		Statement stmt = null;
		try
		{
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
					connection.close();
				}
			}
			catch(Exception e)
			{
			}
		}
	}

	@Override
	public Member selectOne(int no) throws Exception
	{
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select mno,email,mname,cre_date from members where mno=" + no);
			Member member = new Member();
			
			if(rs.next()) // 실행해서 받은 정보가 있을경우
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
					connection.close();
				}
			}
			catch(Exception e)
			{
			}
		}
	}

	@Override
	public int update(Member member) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try
		{
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
					connection.close();
				}
			}
			catch(Exception e)
			{
			}
		}
	}

	@Override
	public Member exist(String email, String password) throws Exception
	{
		PreparedStatement stmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try
		{
			connection = ds.getConnection();
			stmt = connection.prepareStatement("select mname,email from members where email=? and pwd=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if(rs.next())
			{
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
