package service.Impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.ValidationException;

import entities.Project;
import enums.ProjectStatus;
import repository.Impl.ProjectRepositoryImpl;
import service.ProjectService;
import utils.Input;
import utils.StatsHolder;

public class ProjectServiceImpl implements ProjectService {

	private ProjectRepositoryImpl repository = new ProjectRepositoryImpl();
	private Input validator = new Input();
	private static final Logger LOGGER = Logger.getLogger(ProjectServiceImpl.class.getName());

	@Override
	public Project getProject(String idParam) throws ValidationException {
		int id = validator.validateNum(idParam, "Id");

		try {
			return repository.readById(id);
		} catch (SQLException e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
		}
		return null;
	}

	@Override
	public long getProjectCount() {
		try {
			return repository.getCount();
		} catch (SQLException e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
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
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
		}
		return projects;
	}

	@Override
	public Project createProject(String nameInput, String descriptionInput, String startDateInput, String endDateInput)
			throws ValidationException {

		String name = validator.validateStr(nameInput, "Name");
		String description = validator.validateStr(descriptionInput, "Description");
		LocalDate startDate = validator.getLocalDate(startDateInput, false, "Start");
		LocalDate endDate = validator.getLocalDate(endDateInput, true, "End");

		Project project = new Project(name, description, startDate, endDate, ProjectStatus.InPreparation);
		try {
			repository.create(project);
		} catch (SQLException e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
		}
		return project;
	}

	@Override
	public Project updateProject(String nameInput, String descriptionInput, String startDateInput, String endDateInput,
			String statusInput, String idParam) throws ValidationException {

		String name = validator.validateStr(nameInput, "Name");
		String description = validator.validateStr(descriptionInput, "Description");
		LocalDate startDate = validator.getLocalDate(startDateInput, true, "Start");
		LocalDate endDate = validator.getLocalDate(endDateInput, true, "End");
		ProjectStatus projectStatus = ProjectStatus.valueOf(validator.validateStr(statusInput, "Status"));
		int id = validator.validateNum(idParam, "Id");

		Project project = new Project(name, description, startDate, endDate, projectStatus);
		project.setId(id);
		try {
			repository.update(project);
		} catch (SQLException e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
		}
		return project;
	}

	@Override
	public Project deleteProject(String id) throws ValidationException {
		Project project = null;
		int projectId = validator.validateNum(id, "Id");

		try {
			project = repository.readById(projectId);
			if (project == null)
				return null;
			repository.delete(project);
		} catch (SQLException e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
		}
		return project;
	}

	@Override
	public List<Project> searchForProject(String titleInput) throws ValidationException {

		String title = validator.validateStr(titleInput, "Project Title");
		List<Project> projects = null;
		try {
			projects = repository.searchByTitle(title);
		} catch (SQLException e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
		}
		return projects;
	}

	@Override
	public StatsHolder getProjectStats(String id) throws ValidationException {
		int projectId = validator.validateNum(id, "Id");

		return repository.getStats(projectId);
	}
}
