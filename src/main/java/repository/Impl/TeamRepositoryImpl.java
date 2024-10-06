package repository.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Member;
import entities.Team;
import enums.Role;
import repository.ITeamRepository;
import utils.DatabaseConnection;

public class TeamRepositoryImpl implements ITeamRepository {

	private static final Logger logger = LoggerFactory.getLogger(TeamRepositoryImpl.class);
	private final Connection con;

	public TeamRepositoryImpl() throws SQLException {
		loadDriver();
		this.con = DatabaseConnection.getConnection();
	}

	@Override
	public Boolean save(Team team) throws SQLException {
		PreparedStatement psTeam = null;
		PreparedStatement psMember = null;
		boolean result = false;

		try {

			con.setAutoCommit(false);

			String insertTeamSQL = "INSERT INTO teams (name) VALUES (?)";
			psTeam = con.prepareStatement(insertTeamSQL, Statement.RETURN_GENERATED_KEYS);
			psTeam.setString(1, team.getName());
			psTeam.executeUpdate();

			ResultSet generatedKeys = psTeam.getGeneratedKeys();
			if (generatedKeys.next()) {
				Long teamId = generatedKeys.getLong(1);
				team.setId(teamId);

				String insertMemberSQL = "INSERT INTO team_members (team_id, member_id) VALUES (?, ?)";
				psMember = con.prepareStatement(insertMemberSQL);

				for (Member member : team.getMembers()) {
					psMember.setLong(1, teamId);
					psMember.setLong(2, member.getId());
					psMember.addBatch();
				}

				psMember.executeBatch();
			}

			con.commit();
			result = true;
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw e; // Re-throw exception
		}
		return result;
	}

	private void loadDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Team> getAllTeams(int from, int length) throws SQLException {
		String query = "SELECT t.id AS team_id, t.name AS team_name, "
				+ "m.id AS member_id, m.first_name, m.last_name, m.email, m.role " + "FROM teams t "
				+ "LEFT JOIN team_members tm ON t.id = tm.team_id " + "LEFT JOIN members m ON tm.member_id = m.id "
				+ "ORDER BY t.id DESC LIMIT ?, ?";

		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, from);
		pstmt.setInt(2, length);
		ResultSet rs = pstmt.executeQuery();

		Map<Long, Team> teamMap = new HashMap<>();
		while (rs.next()) {
			long teamId = rs.getLong("team_id");
			String teamName = rs.getString("team_name");

			long memberId = rs.getLong("member_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String email = rs.getString("email");
			String role = rs.getString("role");

			Team team = teamMap.get(teamId);
			if (team == null) {
				team = new Team();
				team.setId(teamId);
				team.setName(teamName);
				team.setMembers(new ArrayList<>());
				teamMap.put(teamId, team);
			}

			if (memberId > 0) {
				Member member = new Member();
				member.setId(memberId);
				member.setFirstName(firstName);
				member.setSecondName(lastName);
				member.setEmail(email);
				if (role != null) {
					member.setUserRole(Role.valueOf(role));
				}

				team.getMembers().add(member);
			}
		}

		return new ArrayList<>(teamMap.values());
	}

	@Override
	public int getTeamCount() throws SQLException {
		String query = "SELECT COUNT(*) AS total FROM teams";
		try (PreparedStatement pstmt = con.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				return rs.getInt("total");
			}
		}
		return 0;
	}

	@Override
	public Team getTeamById(Long id) throws SQLException {
		String query = "SELECT t.id AS team_id, t.name AS team_name, "
				+ "m.id AS member_id, m.first_name, m.last_name, m.email, m.role " + "FROM teams t "
				+ "LEFT JOIN team_members tm ON t.id = tm.team_id " + "LEFT JOIN members m ON tm.member_id = m.id "
				+ "WHERE t.id = ?";

		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();

		Team team = null;
		Map<Long, Member> memberMap = new HashMap<>();
		while (rs.next()) {
			long teamId = rs.getLong("team_id");
			String teamName = rs.getString("team_name");

			if (team == null) {
				team = new Team();
				team.setId(teamId);
				team.setName(teamName);
				team.setMembers(new ArrayList<>());
			}

			long memberId = rs.getLong("member_id");
			if (memberId > 0) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String role = rs.getString("role");

				Member member = memberMap.get(memberId);
				if (member == null) {
					member = new Member();
					member.setId(memberId);
					member.setFirstName(firstName);
					member.setSecondName(lastName);
					member.setEmail(email);
					if (role != null) {
						member.setUserRole(Role.valueOf(role));
					}
					memberMap.put(memberId, member);
				}

				team.getMembers().add(member);
			}
		}

		return team;
	}

	@Override
	public Boolean deleteTeam(Long id) throws SQLException {
		String query = "DELETE FROM teams WHERE id = ?";
		try (PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setLong(1, id);

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				logger.info("Team deleted successfully with ID: " + id);
				return true;
			} else {
				logger.warn("No team found with ID: " + id);
				return false;
			}
		} catch (SQLException e) {
			logger.error("Error occurred while deleting team with ID: " + id, e);
			throw e;
		}
	}

}
