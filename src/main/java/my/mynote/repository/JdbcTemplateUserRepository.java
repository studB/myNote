package my.mynote.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import my.mynote.domain.User;

public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateUserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(int id) {
        List<User> result = jdbcTemplate.query("select * from AUTH where id = ?", userRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByUser(String user) {
        List<User> result = jdbcTemplate.query("select * from AUTH where user = ?", userRowMapper(), user);
        return result.stream().findAny();
    }

    private RowMapper<User> userRowMapper() {
        return new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        };

    }

    @Override
    public int createUser(String user, String password) {
        return jdbcTemplate.update("insert into auth (user, password) values (?,?)", user, password);
    }

}
