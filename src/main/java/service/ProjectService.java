package service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.ValidationException;

import entities.Project;

public interface ProjectService {

	public Project getProject(String id) throws ValidationException;

	public List<Project> getAllProjects(int page);

	public Project createProject(String nameInput, String descriptionInput, String startDateInput, String endDateInput)
			throws ValidationException;

	public Project updateProject(String nameInput, String descriptionInput, String startDateInput, String endDateInput,
			String status, String idParam) throws ValidationException;

	public Project deleteProject(String id) throws ValidationException;

	public List<Project> searchForProject(String title) throws SQLException, ValidationException;

	public HashMap<Integer, Integer> getProjectStats(String id);

	long getProjectCount();
}
