package lesson06.controller;

import java.util.Map;

import lesson06.Dao.MySqlMemberDao;
import lesson06.annotation.Component;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller
{
	MySqlMemberDao memberDao;
	
	public MemberDeleteController setMemberDao(MySqlMemberDao memberDao)
	{
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public String execute(Map<String, Object> model) throws Exception
	{
	    Integer no = (Integer)model.get("no");
	    memberDao.delete(no);
	    
	    return "redirect:list.do";
	}
}
