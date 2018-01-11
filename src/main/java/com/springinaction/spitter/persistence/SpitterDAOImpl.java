package com.springinaction.spitter.persistence;

import com.springinaction.spitter.domain.Spitter;
import com.springinaction.spitter.util.exception.SpitterNotFoundException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpitterDAOImpl {
    private static final String SQL_INSERT_SPITTER = "INSERT INTO spitter(username, password, fullname) VALUE (?, ?, ?)";
    private static final String SQL_UPDATE_SPITTER = "UPDATE spitter SET username = ?, password = ?, fullname = ? WHERE id = ?";
    private static final String SQL_DELETE_SPITTER = "DELETE FROM spitter WHERE id = ?";
    private static final String SQL_SELECT_SPITTER_BY_ID = "SELECT * FROM spitter WHERE id = ?";
    private DataSource dataSource;

    public void addSpitter(Spitter spitter) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT_SPITTER)) {
            stmt.setString(1, spitter.getUsername());
            stmt.setString(2, spitter.getPassword());
            stmt.setString(3, spitter.getFullName());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveSpitter(Spitter spitter) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE_SPITTER)) {
            stmt.setString(1, spitter.getUsername());
            stmt.setString(2, spitter.getPassword());
            stmt.setString(3, spitter.getFullName());
            stmt.setLong(4, spitter.getId());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpitter(Spitter spitter) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_DELETE_SPITTER)) {
            stmt.setLong(1, spitter.getId());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Spitter getSpitterById(long id) throws SpitterNotFoundException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_SPITTER_BY_ID)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Spitter spitter = new Spitter();
                    spitter.setId(rs.getLong("id"));
                    spitter.setUsername(rs.getString("username"));
                    spitter.setPassword(rs.getString("password"));
                    spitter.setFullName(rs.getString("fullname"));
                    return spitter;
                } else {
                    throw new SpitterNotFoundException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SpitterNotFoundException();
    }
}
