package lesson06.controller;

import java.util.Map;

import lesson06.annotation.Component;
import lesson06.inter.ProjectDao;

@Component("/project/list.do")
public class ProJectListController implements Controller
{
	ProjectDao projectDao;

	public ProJectListController setMemberDao(ProjectDao projectDao)
	{
		this.projectDao = projectDao;
		return this;
	}
	@Override
	public String execute(Map<String, Object> model) throws Exception
	{
		model.put("project", projectDao.selectList());
		return "/project/ProjectList.jsp";
	}
}
