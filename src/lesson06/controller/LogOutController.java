package lesson06.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import lesson06.Dao.MySqlMemberDao;
import lesson06.annotation.Component;

@Component("/logout.do")
public class LogOutController implements Controller
{
	MySqlMemberDao memberDao;
	public LogOutController setMemberDao(MySqlMemberDao memberDao)
	{
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public String execute(Map<String, Object> model) throws Exception
	{
		HttpSession session = (HttpSession)model.get("session");
	    session.invalidate();
	    return "redirect:/login.do";
	}
}
