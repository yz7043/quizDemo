package com.andy.project1.dao.rowmapper;

import com.andy.project1.domain.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setQuestion_id(rs.getInt("question_id"));
        question.setCategory_id(rs.getInt("category_id"));
        question.setDescription(rs.getString("description"));
        question.setIs_active(rs.getBoolean("is_active"));
        return question;
    }
}
