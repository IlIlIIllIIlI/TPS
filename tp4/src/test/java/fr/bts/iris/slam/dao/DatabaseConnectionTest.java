package fr.bts.iris.slam.dao;

import java.sql.*;
import  org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DatabaseConnectionTest {
    @Test
    void shouldConnectToInMemoryDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:")) {
            assertNotNull(conn);
            assertTrue(conn.isValid(1));
        } catch (SQLException e) {
            fail("Connection failed: " + e.getMessage());
        }
    }
}
