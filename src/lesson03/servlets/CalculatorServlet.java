package lesson03.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


//http://localhost:8880/javaWebDev/calc2?a=15&b=20
@WebServlet("/cal2")
public class CalculatorServlet extends GenericServlet
// 제네릭서블렛을 상속
{
	private static final long serialVersionUID = 1L;

	@Override
	// 제네릭 서블렛에서 이미 다른 메서드들이 구현되어 있음으로 service 메소드만 구현해주면 된다.
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException
	{
		int a = Integer.parseInt(request.getParameter("a"));
		int b = Integer.parseInt(request.getParameter("b"));

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("a=" + a + "," + "b=" + b + "의 계산결과 입니다.");
		writer.println("a + b = " + (a + b));
		writer.println("a - b = " + (a - b));
		writer.println("a * b = " + (a * b));
		writer.println("a / b = " + ((float) a / (float) b));
		writer.println("a % b = " + (a % b));
	}
}
