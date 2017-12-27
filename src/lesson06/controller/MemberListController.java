package lesson06.controller;

import java.util.Map;

import lesson05.dao.MemberDao;

public class MemberListController implements Controller
{
	@Override
	public String execute(Map<String, Object> model) throws Exception
	{
		// constroller 를 구현하여 필요한 객체를 담거나/꺼내 사용한다.
		// memberDao객체를 꺼내 사용한다.
		MemberDao memberDao = (MemberDao)model.get("memberDao");
		// members객체를 model에 담는다.
		model.put("members", memberDao.selectList());
		// 페이지 컨트롤러의 반환값은 화면을 출력할 JSP의 URL을 적어준다. 해당URL로 실행을 위임한다.
		return "/member/MemberList.jsp";
	}
}
