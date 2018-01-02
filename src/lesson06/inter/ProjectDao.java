package lesson06.inter;

import java.util.List;

import lesson06.vo.Project;

public interface ProjectDao
{
	List<Project> selectList() throws Exception;
	int insert(Project project) throws Exception;
	
}
