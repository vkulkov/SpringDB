package com.springinaction.spitter.persistence;

import com.springinaction.spitter.domain.Spitter;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcSpitterDAO implements SpitterDAO {
    private static final String SQL_INSERT_SPITTER = "INSERT INTO spitter(username, password, fullname) VALUE (?, ?, ?)";
    private static final String SQL_UPDATE_SPITTER = "UPDATE spitter SET username = ?, password = ?, fullname = ? WHERE id = ?";
    private static final String SQL_DELETE_SPITTER = "DELETE FROM spitter WHERE id = ?";
    private static final String SQL_SELECT_SPITTER_BY_ID = "SELECT * FROM spitter WHERE id = ?";
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addSpitter(Spitter spitter) {
        jdbcTemplate.update(SQL_INSERT_SPITTER,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFullName());
    }

    @Override
    public void saveSpitter(Spitter spitter) {
        jdbcTemplate.update(SQL_UPDATE_SPITTER,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFullName(),
                spitter.getId());
    }

    @Override
    public void deleteSpitter(Spitter spitter) {
        jdbcTemplate.update(SQL_DELETE_SPITTER,
                spitter.getId());
    }

    @Override
    public Spitter getSpitterById(long id) {
        return jdbcTemplate.queryForObject(
                SQL_SELECT_SPITTER_BY_ID,
                (rs, i) -> {
                    Spitter spitter = new Spitter();
                    spitter.setId(rs.getLong(1));
                    spitter.setUsername(rs.getString(2));
                    spitter.setPassword(rs.getString(3));
                    spitter.setFullName(rs.getString(4));
                    return spitter;
                },
                id
        );
    }
}
