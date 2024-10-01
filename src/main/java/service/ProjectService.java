package service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import entities.Project;

public interface ProjectService {

	public Project getProject(int id) throws SQLException;

	public List<Project> getAllProjects() throws SQLException;

	public Project createProject(Project project) throws SQLException;

	public Project updateProject(Project project) throws SQLException;

	public void deleteProject(Project project) throws SQLException;

	public List<Project> searchForProject(String title) throws SQLException;

	public HashMap<Integer, Integer> getProjectStats(int id);
}
