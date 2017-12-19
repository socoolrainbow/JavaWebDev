package lesson03.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class test implements Servlet
{

	ServletConfig config;
	
	@Override
	public void destroy()
	{
		System.out.println("종료.");
	}

	@Override
	public ServletConfig getServletConfig()
	{
		System.out.println("서블릿 설정.");
		return this.config;
	}

	@Override
	public String getServletInfo()
	{
		System.out.println("서블릿 정보요청.");
		return "testing";
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException
	{
		System.out.println("초기화.");
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException
	{
		System.out.println("서비스 시작.");
	}

}
