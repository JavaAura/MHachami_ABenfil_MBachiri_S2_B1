package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Project;
import service.Impl.ProjectServiceImpl;

public class ProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProjectServiceImpl projectService;

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
		default:
			index(req, resp);
			break;
		}
	}

	protected void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageString = req.getParameter("page");
		int page = 1;
		if (pageString != null)
			page = Integer.parseInt(pageString);

		List<Project> projects = projectService.getAllProjects(page);
		req.setAttribute("projects", projects);

		long totalProjects = projectService.getProjectCount();
		long pageSize = 5;
		long pageNumber = (totalProjects + pageSize - 1) / pageSize;
		req.setAttribute("pageNumbers", pageNumber);

		req.setAttribute("page", page);
		this.getServletContext().getRequestDispatcher("/views/project/index.jsp").forward(req, resp);

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
		else
			store(req, resp);

	}

	protected void store(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("name");
		LocalDate startDate = LocalDate.parse(req.getParameter("start_date"));
		LocalDate endDate = LocalDate.parse(req.getParameter("end_date"));

		Project project = projectService.createProject(name, description, startDate, endDate);

		resp.sendRedirect("/teamsync/project");
	}

	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("project_id"));
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		LocalDate startDate = LocalDate.parse(req.getParameter("start_date"));
		LocalDate endDate = LocalDate.parse(req.getParameter("end_date"));
		String status = req.getParameter("status");

		Project project = projectService.updateProject(name, description, startDate, endDate, status, id);

		resp.sendRedirect("/teamsync/project");
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("project_id");
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		out.println(id);

		Project project = projectService.deleteProject(id);
		if (project == null) {
			req.setAttribute("errorMessage", "Incorrect ID");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("/teamsync/project");
	}

	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("project_id");

		Project project = projectService.getProject(id);

		if (project == null) {
			req.setAttribute("errorMessage", "Incorrect ID");
			req.getRequestDispatcher("/views/project/error.jsp").forward(req, resp);
			return;
		}
		req.setAttribute("project", project);
		req.setAttribute("isUpdate", true);
		this.getServletContext().getRequestDispatcher("/views/project/form.jsp").forward(req, resp);
	}

	protected void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("project_id");

		Project project = projectService.getProject(id);

		if (project == null) {
			req.setAttribute("errorMessage", "Incorrect ID");
			req.getRequestDispatcher("/views/project/error.jsp").forward(req, resp);
			return;
		}
		req.setAttribute("project", project);
		this.getServletContext().getRequestDispatcher("/views/project/detail.jsp").forward(req, resp);
	}

	protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/views/project/form.jsp").forward(req, resp);
	}

	protected void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("q");
		List<Project> projects = projectService.searchForProject(title);
		req.setAttribute("projects", projects);
		this.getServletContext().getRequestDispatcher("/views/project/index.jsp").forward(req, resp);
	}
}
