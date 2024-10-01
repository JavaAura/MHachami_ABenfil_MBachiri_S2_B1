package utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DatabaseConnectionTest {

    private Connection connection;

    // This method will be run before each test
    @BeforeEach
    public void setUp() {
        // Initialize the connection before each test
        connection = DatabaseConnection.getConnection();
    }

    // This method will be run after each test
    @AfterEach
    public void tearDown() throws SQLException {
        // Clean up by closing the connection after each test
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testConnectionIsNotNull() {
        assertNotNull(connection, "The connection should not be null.");
    }

    @Test
    public void testConnectionIsValid() throws SQLException {
        assertTrue(connection != null && !connection.isClosed(), "The connection should be valid and open.");
    }
}
