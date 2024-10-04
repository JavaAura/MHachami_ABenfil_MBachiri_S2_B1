package repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import entities.Project;

public interface ProjectRepository {

	List<Project> read(int from, int length) throws SQLException;

	Project readById(int id) throws SQLException;

	Project create(Project project) throws SQLException;

	Project update(Project project) throws SQLException;

	void delete(Project project) throws SQLException;

	List<Project> searchByTitle(String title) throws SQLException;

	public HashMap<Integer, Integer> getStats(int id);

	Long getCount() throws SQLException;
}
