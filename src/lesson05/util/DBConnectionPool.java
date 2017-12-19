package lesson05.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBConnectionPool
{
	String url;
	String username;
	String password;
	ArrayList<Connection> connList = new ArrayList<Connection>();
	
	public DBConnectionPool(String driver, String url, String username, String password) throws Exception
	{
		this.url = url;
		this.password = password;
		this.username = username;
		
		Class.forName(driver);
	}
	
	public Connection getConnection() throws Exception
	{
		// 생성되어있는 커넥션의 유효성검사
		if(connList.size() > 0)
		{
			Connection conn = connList.get(0);
			// isValid 매서드로 확인
			if(conn.isValid(10))
			{
				return conn;
			}
		}
		// ArryList에 보관된 객체가 없다면, DriverManager를 통해 새로 만들어 반환. 
		return DriverManager.getConnection(url, username, password);
	}
	
	public void returnConnection(Connection conn) throws Exception
	{
		connList.add(conn);
	}
	
	public void closeAll()
	{
		for(Connection conn : connList)
		{
			try
			{
				conn.close();
			}
			catch(Exception e)
			{
			}
		}
	}
}
