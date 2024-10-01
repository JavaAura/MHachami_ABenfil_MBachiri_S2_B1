package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Project;
import service.Impl.ProjectServiceImpl;
import utils.DatabaseConnection;

public class ProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProjectServiceImpl projectServiceImpl;

	@Override
	public void init() throws ServletException {
		Connection connection = DatabaseConnection.getConnection();
		projectServiceImpl = new ProjectServiceImpl(connection);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("views/project/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("name");
		LocalDate startDate = LocalDate.parse(req.getParameter("start_date"));
		LocalDate endDate = LocalDate.parse(req.getParameter("end_date"));

		Project project = new Project(name, description, startDate, endDate, new Project().getStatus());
		try {
			projectServiceImpl.createProject(project);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/projects/index").forward(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("project_id"));
		Project project = new Project(id);

		try {
			projectServiceImpl.deleteProject(project);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("project_id"));
		String name = req.getParameter("name");
		String description = req.getParameter("name");
		LocalDate startDate = LocalDate.parse(req.getParameter("start_date"));
		LocalDate endDate = LocalDate.parse(req.getParameter("end_date"));

		Project project = new Project(name, description, startDate, endDate, new Project().getStatus());
		project.setId(id);
		try {
			projectServiceImpl.updateProject(project);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/projects/index").forward(req, resp);
	}
}
