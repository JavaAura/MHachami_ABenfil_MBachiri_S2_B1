package service.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import entities.Project;
import repository.Impl.ProjectRepositoryImpl;
import service.ProjectService;

public class ProjectServiceImpl implements ProjectService {
	private final ProjectRepositoryImpl repository;

	public ProjectServiceImpl(Connection connection) {
		repository = new ProjectRepositoryImpl(connection);
	}

	@Override
	public Project getProject(int id) throws SQLException {
		return repository.readById(id);
	}

	@Override
	public List<Project> getAllProjects() throws SQLException {
		return repository.read();
	}

	@Override
	public Project createProject(Project project) throws SQLException {
		return repository.create(project);
	}

	@Override
	public Project updateProject(Project project) throws SQLException {
		return repository.update(project);
	}

	@Override
	public void deleteProject(Project project) throws SQLException {
		repository.delete(project);
	}

	@Override
	public List<Project> searchForProject(String title) throws SQLException {
		return repository.searchByTitle(title);
	}

	@Override
	public HashMap<Integer, Integer> getProjectStats(int id) {
		return null;
	}
}
