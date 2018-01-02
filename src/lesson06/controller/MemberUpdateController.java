package lesson06.controller;

import java.util.Map;

import lesson05.vo.Member;
import lesson06.Dao.MySqlMemberDao;
import lesson06.annotation.Component;

@Component("/member/delete.do")
public class MemberUpdateController implements Controller
{
	MySqlMemberDao memberDao;

	public MemberUpdateController setMemberDao(MySqlMemberDao memberDao)
	{
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception
	{
//		MemberDao memberDao = (MemberDao) model.get("memberDao");

		// list에서 oneRow 선택
		if(model.get("member") == null)
		{
			Integer no = (Integer) model.get("no");
			Member member = memberDao.selectOne(no);
			model.put("member", member);
			return "/member/MemberUpdateForm.jsp";
		}
		// update Form에서 확인버튼
		else
		{
			Member member = (Member) model.get("member");
			memberDao.update(member);
			return "redirect:list.do";
		}
	}
}
