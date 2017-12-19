package lesson04.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

// 필터도 애노테이션을 이용하여 배치가 가능하다.
@WebFilter(
		urlPatterns="/*",
		initParams= {@WebInitParam(name="encoding",value="UTF-8")}		
		)


public class CharacterEncodingFilter implements Filter
{
	FilterConfig config;
	
	// init 메서드 : 필터 객체가 생성되고 나서 준비 작업을 위해 딱 한번 호출된다.
	@Override
	public void init(FilterConfig config) throws ServletException
	{
		// FilterConfig 클래스 객체를 doFilter에 사용하기위해 인스턴트 변수에 저장.
		this.config = config;
	}
	
	// 필터와 연결된 URL에 대해 요청이 들어오면 doFilter가 항상 호출된다.
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
			throws IOException, ServletException
	{
		/* 서블릿이 실행되기 전에 해야할 작업 */
		req.setCharacterEncoding(config.getInitParameter("encoding"));
		
		// 다음 필터를 호출. 더이상 필터가 없다면 서블릿의 service()가 호출됨.
		fc.doFilter(req, res);
		
		/* 서블릿을 실행한 후, 클라이언트에게 응답하기 전에 해야할 작업 */
		
	}
	
	// 서블릿 컨테이너는 웹 애플리케이션을 종료하기 전에 필터들에 대해 destroy를 호출하여 마무리 작업을 할 수 있는 기회를 준다.
	@Override
	public void destroy()
	{
	}

}
