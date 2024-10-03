package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import entities.Project;

public interface ProjectService {

	public Project getProject(String id);

	public List<Project> getAllProjects();

	public Project createProject(String name, String description, LocalDate startDate, LocalDate endDate);

	public Project updateProject(String name, String description, LocalDate startDate, LocalDate endDate, String status,
			int id);

	public Project deleteProject(String id);

	public List<Project> searchForProject(String title) throws SQLException;

	public HashMap<Integer, Integer> getProjectStats(int id);
}
