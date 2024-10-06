package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import entities.Project;
import entities.Team;
import repository.Impl.TeamRepositoryImpl;
import service.Impl.ProjectServiceImpl;
import utils.ProjectStatusUtil;
import utils.StatsHolder;

public class ProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProjectServiceImpl projectService;
	private static final Logger LOGGER = Logger.getLogger(ProjectServlet.class.getName());

	@Override
	public void init() throws ServletException {
		projectService = new ProjectServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (!path.equals("/project"))
			path = path.substring("/project/".length());

		switch (path) {
		case "create":
			create(req, resp);
			break;
		case "edit":
			edit(req, resp);
			break;
		case "search":
			search(req, resp);
			break;
		case "show":
			show(req, resp);
			break;
		case "assign":
			assignView(req, resp);
			break;
		default:
			index(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		if (!action.equals("/project"))
			action = action.substring("/project/".length());

		if (action.equals("delete"))
			delete(req, resp);
		else if (action.equals("update"))
			update(req, resp);
		else if (action.equals("assign"))
			assign(req, resp);
		else
			store(req, resp);
	}

	protected void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageString = req.getParameter("page");
		int page = 1;
		if (pageString != null && pageString.matches("-?\\d+(\\.\\d+)?"))
			page = Integer.parseInt(pageString);

		List<Project> projects = projectService.getAllProjects(page);
		req.setAttribute("projects", projects);

		long totalProjects = projectService.getProjectCount();
		int pageSize = 5;
		long pageNumbers = (totalProjects + pageSize - 1) / pageSize;

		req.setAttribute("totalProjects", totalProjects);
		req.setAttribute("page", page);
		req.setAttribute("pageNumbers", pageNumbers);
		req.setAttribute("pageSize", pageSize);
		this.getServletContext().getRequestDispatcher("/views/project/index.jsp").forward(req, resp);
	}

	protected void store(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String startDate = req.getParameter("start_date");
		String endDate = req.getParameter("end_date");

		try {
			Project project = projectService.createProject(name, description, startDate, endDate);
			req.setAttribute("successMessage", "Project Created Successfully");
			index(req, resp);
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
			req.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/project/form.jsp");
			dispatcher.forward(req, resp);
		}
	}

	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("Entering update method");
		String id = req.getParameter("project_id");
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String startDate = req.getParameter("start_date");
		String endDate = req.getParameter("end_date");
		String status = req.getParameter("status");

		try {
			LOGGER.info("Calling projectService.updateProject");
			Project project = projectService.updateProject(name, description, startDate, endDate, status, id);
			LOGGER.info("Project updated successfully, redirecting");
			req.setAttribute("successMessage", "Project updated Successfully");
			index(req, resp);
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
			req.setAttribute("errorMessage", errorMessage);
			edit(req, resp);
		}
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("project_id");
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		out.println(id);

		try {
			Project project = projectService.deleteProject(id);
			if (project == null) {
				req.setAttribute("errorMessage", "Incorrect ID");
				req.getRequestDispatcher("/views/error-404.jsp").forward(req, resp);
				return;
			}
			req.setAttribute("successMessage", "Project Deleted Successfully");
			index(req, resp);
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/views/error-404.jsp").forward(req, resp);
		}
	}

	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("project_id");

		try {
			Project project = projectService.getProject(id);
			if (project == null) {
				req.setAttribute("errorMessage", "Incorrect ID");
				req.getRequestDispatcher("/views/error-404.jsp").forward(req, resp);
				return;
			}
			req.setAttribute("project", project);
			req.setAttribute("isUpdate", true);
			this.getServletContext().getRequestDispatcher("/views/project/form.jsp").forward(req, resp);
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/views/error-404.jsp").forward(req, resp);
		}
	}

	protected void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("project_id");

		try {
			Project project = projectService.getProject(id);

			if (project == null) {
				req.setAttribute("errorMessage", "Incorrect ID");
				req.getRequestDispatcher("/views/error-404.jsp").forward(req, resp);
				return;
			}

			StatsHolder statsHolder = projectService.getProjectStats(id);

			req.setAttribute("memberCount", statsHolder.getMemberCount());
			req.setAttribute("taskCount", statsHolder.getTakCount());
			req.setAttribute("project", project);

			req.setAttribute("statusUtil", new ProjectStatusUtil());

			if (project.getTeamId() != 0) {
				try {
					TeamRepositoryImpl tImpl = new TeamRepositoryImpl();
					Team team = tImpl.getTeamById((long) project.getTeamId());
					req.setAttribute("team", team);
				} catch (SQLException e) {
					String errorMessage = e.getMessage();
					LOGGER.warning("error: " + errorMessage);
					req.setAttribute("errorMessage", errorMessage);
				}
			}

			this.getServletContext().getRequestDispatcher("/views/project/detail.jsp").forward(req, resp);
		} catch (ValidationException e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/views/error-404.jsp").forward(req, resp);
		}
	}

	protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/views/project/form.jsp").forward(req, resp);
	}

	protected void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("q");

		try {
			List<Project> projects = projectService.searchForProject(title);
			req.setAttribute("projects", projects);
			req.setAttribute("title", title);
			this.getServletContext().getRequestDispatcher("/views/project/index.jsp").forward(req, resp);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
			req.setAttribute("errorMessage", errorMessage);
			index(req, resp);
		}
	}

	private void assignView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String projectIdParam = req.getParameter("project_id");
		List<Team> teams;

		try {
			TeamRepositoryImpl tImpl = new TeamRepositoryImpl();
			teams = tImpl.getAllTeams(0, tImpl.getTeamCount());

		} catch (SQLException e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
			req.setAttribute("errorMessage", errorMessage);
			index(req, resp);
			return;
		}
		try {
			Project project = projectService.getProject(projectIdParam);
			req.setAttribute("teams", teams);
			req.setAttribute("project", project);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/project/assign.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
			req.setAttribute("errorMessage", errorMessage);
			index(req, resp);
		}
	}

	private void assign(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String teamId = req.getParameter("team_id");
		String projectId = req.getParameter("project_id");

		try {
			boolean checker = projectService.assignProjectToTeam(teamId, projectId);
			if (!checker) {
				String errorMessage = "Something went wrong";
				LOGGER.warning("error: " + errorMessage);
				req.setAttribute("errorMessage", errorMessage);
			} else
				req.setAttribute("successMessage", "Project Assigned Successfully");

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
			req.setAttribute("errorMessage", errorMessage);
		}

		index(req, resp);
	}

}
