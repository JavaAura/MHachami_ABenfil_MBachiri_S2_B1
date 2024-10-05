package repository.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entities.Project;
import enums.ProjectStatus;
import repository.ProjectRepository;
import utils.DatabaseConnection;
import utils.StatsHolder;

public class ProjectRepositoryImpl implements ProjectRepository {
	private Connection connection;
	private static final Logger LOGGER = Logger.getLogger(ProjectRepositoryImpl.class.getName());

	public ProjectRepositoryImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
		}
		this.connection = DatabaseConnection.getConnection();
	}

	@Override
	public List<Project> read(int from, int length) throws SQLException {
		String query = "SELECT * FROM projects ORDER BY id DESC LIMIT ?, ?";

		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, from);
		pstmt.setInt(2, length);
		ResultSet rs = pstmt.executeQuery();
		List<Project> projects = new ArrayList<>();
		Project project;

		while (rs.next()) {
			project = setProject(rs);
			projects.add(project);
		}
		return projects;
	}

	@Override
	public Long getCount() throws SQLException {
		String query = "SELECT COUNT( * ) as count FROM projects";

		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery(query);
		long count = 0;

		if (rs.next()) {
			count = rs.getLong("count");
		}
		return count;
	}

	@Override
	public Project readById(int id) throws SQLException {
		String query = "SELECT * FROM projects WHERE id=?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		Project project = null;

		if (rs.next())
			project = setProject(rs);

		return project;
	}

	private Project setProject(ResultSet rs) throws SQLException {
		Project project;
		project = new Project();
		project.setId(rs.getInt("id"));
		project.setName(rs.getString("name"));
		project.setDescription(rs.getString("description"));
		project.setStartDate(LocalDate.parse(rs.getString("start_date")));
		project.setEndDate(LocalDate.parse(rs.getString("end_date")));
		project.setStatus(ProjectStatus.valueOf(rs.getString("status")));
		return project;
	}

	@Override
	public Project create(Project project) throws SQLException {
		String query = "INSERT INTO projects (name, description, start_date, end_date) VALUES (?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, project.getName());
		pstmt.setString(2, project.getDescription());
		pstmt.setString(3, project.getStartDate().toString());
		pstmt.setString(4, project.getEndDate().toString());
		pstmt.executeUpdate();
		return project;
	}

	@Override
	public Project update(Project project) throws SQLException {
		String query = "UPDATE projects SET name=?, description=?, start_date=?, end_date=?, status=? WHERE id=?";

		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, project.getName());
		pstmt.setString(2, project.getDescription());
		pstmt.setString(3, project.getStartDate().toString());
		pstmt.setString(4, project.getEndDate().toString());
		pstmt.setString(5, project.getStatus().toString());
		pstmt.setInt(6, project.getId());
		pstmt.executeUpdate();
		return project;
	}

	@Override
	public void delete(Project project) throws SQLException {
		String query = "DELETE FROM projects WHERE id=?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, project.getId());
		pstmt.executeUpdate();
	}

	@Override
	public List<Project> searchByTitle(String title) throws SQLException {
		String query = "SELECT * FROM projects WHERE name LIKE ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		String param = "%" + title + "%";

		pstmt.setString(1, param);
		ResultSet rs = pstmt.executeQuery();
		List<Project> projects = new ArrayList<>();

		while (rs.next()) {
			Project project;
			project = setProject(rs);
			projects.add(project);
		}

		return projects;
	}

	@Override
	public StatsHolder getStats(int id) {
		String memberCountQuery = "SELECT COUNT(DISTINCT tm.member_id) AS member_count FROM team_projects tp "
				+ "JOIN team_members tm ON tp.team_id = tm.team_id WHERE tp.project_id = ?";

		String taskCountQuery = "SELECT COUNT(DISTINCT mt.task_id) AS task_count FROM team_projects tp "
				+ "JOIN team_members tm ON tp.team_id = tm.team_id "
				+ "JOIN member_tasks mt ON tm.member_id = mt.member_id WHERE tp.project_id = ?";

		StatsHolder statsHolder = new StatsHolder();

		try {
			PreparedStatement memberPstmt = connection.prepareStatement(memberCountQuery);
			memberPstmt.setInt(1, id);
			ResultSet memberRs = memberPstmt.executeQuery();

			if (memberRs.next())
				statsHolder.setMemberCount(memberRs.getInt("member_count"));

			memberRs.close();
			memberPstmt.close();

			PreparedStatement taskPstmt = connection.prepareStatement(taskCountQuery);
			taskPstmt.setInt(1, id);
			ResultSet taskRs = taskPstmt.executeQuery();

			if (taskRs.next())
				statsHolder.setTaskCount(taskRs.getInt("task_count"));

			taskRs.close();
			taskPstmt.close();

		} catch (SQLException e) {
			String errorMessage = e.getMessage();
			LOGGER.warning("error: " + errorMessage);
		}

		return statsHolder;
	}
}
