package com.andy.project1.dao.rowmapper;

import com.andy.project1.domain.Quiz;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizRowMapper implements RowMapper<Quiz> {
    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz quiz = new Quiz();
        quiz.setQuiz_id(rs.getInt("quiz_id"));
        quiz.setUser_id(rs.getInt("user_id"));
        quiz.setCategory_id(rs.getInt("category_id"));
        quiz.setName(rs.getString("name"));
        quiz.setTime_start(rs.getTimestamp("time_start"));
        quiz.setTime_end(rs.getTimestamp("time_end"));
        return quiz;
    }
}
