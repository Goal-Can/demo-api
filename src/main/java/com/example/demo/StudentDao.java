package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Student> rowMapper = new RowMapper<Student>() {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getDouble("score"),
                    rs.getString("gender")
            );
        }
    };

    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Student findById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void insert(Student s) {
        String sql = "INSERT INTO students (name, age, score, gender) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, s.getName(), s.getAge(), s.getScore(), s.getGender());
    }

    public void update(Student s) {
        String sql = "UPDATE students SET name = ?, age = ?, score = ?, gender = ? WHERE id = ?";
        jdbcTemplate.update(sql, s.getName(), s.getAge(), s.getScore(), s.getGender(), s.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}