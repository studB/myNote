package my.mynote.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import my.mynote.domain.Content;

public class JdbcTemplateTextRepository implements TextRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateTextRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Content> findById(int id) {
        List<Content> result = jdbcTemplate.query("select * from CONTENT where id = ?", contentRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Content> findByTitle(String username, String title) {
        List<Content> result = jdbcTemplate.query("select * from CONTENT where (username, title) = (?,?)",
                contentRowMapper(), username, title);
        return result.stream().findAny();
    }

    @Override
    public List<Content> findByUsername(String username) {
        return jdbcTemplate.query("select * from CONTENT where username = ?", contentRowMapper(), username);
    }

    private RowMapper<Content> contentRowMapper() {
        return new RowMapper<Content>() {

            @Override
            public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
                Content content = new Content();
                content.setId(rs.getInt("id"));
                content.setTitle(rs.getString("title"));
                content.setUsername(rs.getString("username"));
                content.setBody(rs.getString("body"));
                return content;
            }
        };
    }

    @Override
    public int create(Content content) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        Map<String, Object> params = new HashMap<>();
        params.put("username", content.getUsername());
        params.put("title", content.getTitle());
        params.put("body", content.getBody());
        Number key = jdbcInsert.withTableName("CONTENT").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(params));

        return key.intValue();

    }

    @Override
    public int Updatebody(Content content) {

        jdbcTemplate.update("update content set body = ? where (username,title) = (?,?)", content.getBody(),
                content.getUsername(), content.getTitle());

        return 0;
    };

    @Override
    public int deleteContent(Content content) {
        return jdbcTemplate.update("delete from content where (username, title) = (?,?)", content.getUsername(),
                content.getTitle());

    }
}
