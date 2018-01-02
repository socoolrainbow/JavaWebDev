package lesson06.context;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.reflections.Reflections;

import lesson06.annotation.Component;

//프로퍼티 파일 및 애노테이션을 이용한 객체 준비
public class ApplicationContext
{
	Hashtable<String, Object> objTable = new Hashtable<String, Object>();

	public Object getBean(String key)
	{
		return objTable.get(key);
	}

	public ApplicationContext(String propertiesPath) throws Exception
	{
		Properties props = new Properties();
		props.load(new FileReader(propertiesPath));

		prepareObjects(props);
		prepareAnnotationObjects();
		injectDependency();
	}

	private void prepareAnnotationObjects() throws Exception
	{
		Reflections reflector = new Reflections("");

		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
		String key = null;
		for(Class<?> clazz : list)
		{
			key = clazz.getAnnotation(Component.class).value();
			objTable.put(key, clazz.newInstance());
		}
	}

	private void prepareObjects(Properties props) throws Exception
	{
		Context ctx = new InitialContext();
		String key = null;
		String value = null;

		for(Object item : props.keySet())
		{
			key = (String) item;
			value = props.getProperty(key);
			if(key.startsWith("jndi."))
			{
				objTable.put(key, ctx.lookup(value));
			}
			else
			{
				objTable.put(key, Class.forName(value).newInstance());
			}
		}
	}

	private void injectDependency() throws Exception
	{
		for(String key : objTable.keySet())
		{
			if(!key.startsWith("jndi."))
			{
				callSetter(objTable.get(key));
			}
		}
	}

	private void callSetter(Object obj) throws Exception
	{
		Object dependency = null;
		for(Method m : obj.getClass().getMethods())
		{
			if(m.getName().startsWith("set"))
			{
				dependency = findObjectByType(m.getParameterTypes()[0]);
				if(dependency != null)
				{
					m.invoke(obj, dependency);
				}
			}
		}
	}

	private Object findObjectByType(Class<?> type)
	{
		for(Object obj : objTable.values())
		{
			if(type.isInstance(obj))
			{
				return obj;
			}
		}
		return null;
	}
}


/*public class ApplicationContext
{
	// 프로퍼티에 설정된 대로 객체를 준비하면, 객체를 저장할 보관소로 해시테이블을 준비한다.
	Hashtable<String, Object> objTalbe = new Hashtable<String, Object>();

	// 해시테이블에서 객체를 꺼내는 매서드
	public Object getBean(String key)
	{
		return objTalbe.get(key);
	}

	// 클래스 생성자
	public ApplicationContext(String propertiesPath) throws Exception
	{
		// 호출되면 매개변수로 지정된 프로퍼티 파일의 내용을 로딩해야 한다.
		// Properies 객체는 이름=값 형태로 된 파일을 다룰때 사용하는 클래스
		Properties props = new Properties();
		props.load(new FileReader(propertiesPath));

		prepareObjects(props);
		prepareAnnotationObjects();
		injectDependency();
	}
	
	public void prepareAnnotationObjects() throws Exception
	{
		Reflections reflector = new Reflections();
		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
		String key = null;
		for(Class<?> clazz : list)
		{
			key = clazz.getAnnotation(Component.class).value();
			objTalbe.put(key, clazz.newInstance());
		}
	}
	
	// 객체의 준비
	private void prepareObjects(Properties props) throws Exception
	{
		Context ctx = new InitialContext();
		String key = null;
		String value = null;
		
		// properties에 들어있는 정보를 꺼내서 객체를 생성한다.
		for(Object item : props.keySet())
		{
			key = (String) item;
			value = props.getProperty(key);
			if(key.startsWith("jndi."))
			{
				// initalContext의 lookUp() 메서드는 JNDI인터페이스를 통해 톰캣 서버에 등록된 객체를 찾는다.
				objTalbe.put(key, ctx.lookup(value));
			}
			else
			{
				//객체 테이블 objTable에 저장
				objTalbe.put(key, Class.forName(value).newInstance());
			}
		}
	}

	// 톰캣 서버로부터 객체를 가져오거나, 직접 객체를 생성했으면, 각 객체가 필요로 하는 의존 객체를 할당한다.
	private void injectDependency() throws Exception
	{
		for(String key : objTalbe.keySet())
		{
			// 객체 이름이 .jndi로 시작하는 경우 톰캣 서버에서 제공한 객체이므로 의존 객체를 주입해서는 안된다.
			if(!key.startsWith("jndi."))
			{
				callSetter(objTalbe.get(key));
			}
		}
	}

	// 매개변수로 주어진 객체에 대해 셋터 메서드를 찾아서 호출한다.
	private void callSetter(Object obj) throws Exception
	{
		Object dependency = null;
		for(Method m : obj.getClass().getMethods())
		{
			// set 메서드일경우
			if(m.getName().startsWith("set"))
			{
				// 셋터 메서드를 찾았으면 셋터 메서드의 매개변수와 타입이 일치하는 객체를 objTable에서 찾는다.
				dependency = findObjectByType(m.getParameterTypes()[0]);
				if(dependency != null)
				{
					// 의존 객체를 찾았으면, 셋터 매서드를 호출한다.
					m.invoke(obj, dependency);
				}
			}
		}
	}

	// 셋터 메서드를 호출할 때 넘겨줄 의존 객체를 찾는다.
	private Object findObjectByType(Class<?> typw)
	{
		// 셋터 메서드의 매개변수 타입과 일치하는 객체를 찾았다면 그 객체의 주소를 리턴한다.
		for(Object obj : objTalbe.values())
		{
			return obj;
		}
		return null;
	}
}
*/