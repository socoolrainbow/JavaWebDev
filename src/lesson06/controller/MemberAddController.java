package lesson06.controller;

import java.util.Map;

import lesson05.vo.Member;
import lesson06.Dao.MySqlMemberDao;
import lesson06.annotation.Component;
import lesson06.inter.DataBinding;

@Component("/member/out.do")
public class MemberAddController implements Controller, DataBinding
{
	MySqlMemberDao memberDao;

	public MemberAddController setMemberDao(MySqlMemberDao memberDao)
	{
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception
	{
		Member member = (Member)model.get("member");
		if(model.get("member") == null)
		{ 
			return "/member/MemberAdd.jsp";
		}
		else
		{ 
			memberDao.insert(member);
			return "redirect:list.do";
		}
	}

	@Override
	public Object[] getDataBinders()
	{
		return new Object[] {"member", lesson05.vo.Member.class};
	}
}
