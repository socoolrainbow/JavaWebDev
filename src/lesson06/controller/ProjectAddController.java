package lesson06.controller;

import java.util.Map;

import lesson06.inter.ProjectDao;
import lesson06.vo.Project;


public class ProjectAddController implements Controller
{
	ProjectDao projectDao;

	public ProjectAddController setProjectDao(ProjectDao projectDao)
	{
		this.projectDao = projectDao;
		return this;
	}

	public Object[] getDataBinders()
	{
		return new Object[]
		{"project", lesson06.vo.Project.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception
	{
		Project project = (Project) model.get("project");
		if(project.getTitle() == null)
		{
			return "/project/ProjectForm.jsp";
		}
		else
		{
			projectDao.insert(project);
			return "redirect:list.do";
		}
	}

}
