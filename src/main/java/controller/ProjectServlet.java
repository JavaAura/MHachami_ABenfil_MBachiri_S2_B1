package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Project;
import repository.Impl.ProjectRepositoryImpl;

public class ProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProjectRepositoryImpl projectRepo;

	@Override
	public void init() throws ServletException {
		projectRepo = new ProjectRepositoryImpl();
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
		default:
			index(req, resp);
			break;
		}
	}

	protected void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Project> projects = null;
		resp.setContentType("text/plain");
		try {
			projects = projectRepo.read();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		req.setAttribute("projects", projects);
		this.getServletContext().getRequestDispatcher("/views/project/index.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("name");
		LocalDate startDate = LocalDate.parse(req.getParameter("start_date"));
		LocalDate endDate = LocalDate.parse(req.getParameter("end_date"));

		Project project = new Project(name, description, startDate, endDate, new Project().getStatus());
		try {
			projectRepo.create(project);
		} catch (SQLException e) {
			e.printStackTrace();
		}

//		this.getServletContext().getRequestDispatcher("/projects/index").forward(req, resp);
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("project_id"));
		Project project = new Project(id);

		try {
			projectRepo.delete(project);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("project_id"));
		String name = req.getParameter("name");
		String description = req.getParameter("name");
		LocalDate startDate = LocalDate.parse(req.getParameter("start_date"));
		LocalDate endDate = LocalDate.parse(req.getParameter("end_date"));

		Project project = new Project(name, description, startDate, endDate, new Project().getStatus());
		project.setId(id);
		try {
			projectRepo.update(project);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/projects/index").forward(req, resp);
	}

	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("project_id"));
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		out.println(id);
	}

	protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	protected void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
