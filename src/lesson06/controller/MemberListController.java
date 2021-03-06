package lesson06.controller;

import java.util.Map;

import lesson06.Dao.MySqlMemberDao;
import lesson06.annotation.Component;

@Component("/member/list.do")
public class MemberListController implements Controller
{
	MySqlMemberDao memberDao;
	
	public MemberListController setMemberDao(MySqlMemberDao memberDao)
	{
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception
	{
		model.put("members", memberDao.selectList());
		return "/member/MemberList.jsp";
	}
}
