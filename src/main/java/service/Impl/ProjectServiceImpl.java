package service.Impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import entities.Project;
import enums.ProjectStatus;
import repository.Impl.ProjectRepositoryImpl;
import service.ProjectService;

public class ProjectServiceImpl implements ProjectService {

	private ProjectRepositoryImpl repository = new ProjectRepositoryImpl();

	@Override
	public Project getProject(String id) {
		if (id == null)
			return null;

		try {
			return repository.readById(Integer.parseInt(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getProjectCount() {
		try {
			return repository.getCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Project> getAllProjects(int page) {

		int from = 0;
		int length = 5;

		if (page > 1) {
			from = length * (page - 1);
		}

		List<Project> projects = null;
		try {
			projects = repository.read(from, length);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}

	@Override
	public Project createProject(String name, String description, LocalDate startDate, LocalDate endDate) {
		Project project = new Project(name, description, startDate, endDate, new Project().getStatus());
		try {
			repository.create(project);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return project;
	}

	@Override
	public Project updateProject(String name, String description, LocalDate startDate, LocalDate endDate, String status,
			int id) {
		ProjectStatus projectStatus = ProjectStatus.valueOf(status);
		Project project = new Project(name, description, startDate, endDate, projectStatus);
		project.setId(id);
		try {
			repository.update(project);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return project;
	}

	@Override
	public Project deleteProject(String id) {
		if (id == null)
			return null;
		Project project = null;
		int projectId = Integer.parseInt(id);
		try {
			project = repository.readById(projectId);
			if (project == null)
				return null;
			repository.delete(project);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return project;
	}

	@Override
	public List<Project> searchForProject(String title) {

		List<Project> projects = null;
		try {
			projects = repository.searchByTitle(title);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}

	@Override
	public HashMap<Integer, Integer> getProjectStats(int id) {
		return null;
	}
}
