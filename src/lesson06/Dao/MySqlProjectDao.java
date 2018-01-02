package lesson06.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import lesson06.inter.ProjectDao;
import lesson06.vo.Project;

public class MySqlProjectDao implements ProjectDao
{
	DataSource ds;
	
	public void setDataSource(DataSource ds)
	{
		this.ds = ds;
	}
	@Override
	public List<Project> selectList() throws Exception
	{
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try
		{
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select pno, pname, sta_date, end_date from projects order by pno desc");
			ArrayList<Project> projects = new ArrayList<Project>();
			
			while(rs.next())
			{
				projects.add(new Project()
						.setNo(rs.getInt("pno"))
						.setTitle(rs.getString("pname"))
						.setStartDate(rs.getDate("sta_date"))
						.setEndDate(rs.getDate("end_date"))
						.setState(rs.getInt("state")));
			}
			return projects;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally {
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
	public int insert(Project project) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;
		try
		{
			connection = ds.getConnection();
			stmt = connection.prepareStatement("insert into projects pname, content, sta_date, end_date, state, cre_date, tags values (?,?,?,?,0,now(),?)");
			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
			stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
			stmt.setString(5, project.getTags());
			
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
	
}
