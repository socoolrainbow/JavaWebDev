package lesson06.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import lesson05.vo.Member;
import lesson06.Dao.MySqlMemberDao;
import lesson06.annotation.Component;

@Component("/login.do")
public class LogInController implements Controller
{
	MySqlMemberDao memberDao;
	
	public LogInController setMemberDao(MySqlMemberDao memberDao)
	{
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception
	{
		if(model.get("loginInfo") == null)
		{ // 입력폼을 요청할 때
			return "/LogInForm.jsp";

		}
		else
		{ // 회원 등록을 요청할 때
//			MemberDao memberDao = (MemberDao) model.get("memberDao");
			Member loginInfo = (Member) model.get("loginInfo");

			Member member = memberDao.exist(loginInfo.getEmail(), loginInfo.getPassword());

			if(member != null)
			{
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("member", member);
				return "redirect:/member/list.do";
			}
			else
			{
				return "/LogInFail.jsp";
			}
		}
	}
}
