package lesson05.servlet;

import java.util.ListResourceBundle;

public class MyResourceBundle extends ListResourceBundle
{
	public Object[][] getContents()
	{
		return new Object[][]{
			{"OK","확인"},
			{"Cancel","취소"},
			{"Reset","재설정"},
			{"Submit","제출"}
		};
	}
}
