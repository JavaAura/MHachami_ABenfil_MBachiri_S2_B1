package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection {
	private static Connection connection = null;
	private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);

	static {
//		Dotenv dotenv = Dotenv.configure().load();

		// Access environment variables
		String url = "jdbc:mysql://localhost:3306/team_sync";
		String user = "root";
		String password = "";

		try {
			// Attempt to connect to the database
			connection = DriverManager.getConnection(url, user, password);
			logger.info("Connected to the database.");
		} catch (SQLException e) {
			logger.error("Connection failed. Check output console for more details.", e);
		}
	}

	public static Connection getConnection() {
		if (connection == null) {
			logger.error("Database connection is null.");
		}
		return connection;
	}
}
