package com.andy.project1.dao;

import com.andy.project1.dao.rowmapper.QuestionRowMapper;
import com.andy.project1.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDao {
    private final JdbcTemplate jdbcTemplate;
    private final QuestionRowMapper questionRowMapper;

    @Autowired
    public QuestionDao(JdbcTemplate jdbcTemplate, QuestionRowMapper questionRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.questionRowMapper = questionRowMapper;
    }

    public Question getQuestionById(Integer id){
        String sql = "SELECT * FROM Question WHERE question_id = ?";
        List<Question> result = jdbcTemplate.query(sql, questionRowMapper, id);
        return result.size() == 0 ? null : result.get(0);
    }

    public List<Question> getAllQuestions(){
        String sql = "SELECT * FROM Question";
        return jdbcTemplate.query(sql, questionRowMapper);
    }

    public List<Question> getQuestionsByCategory(Integer category_id){
        String sql = "SELECT * FROM Question WHERE category_id = ?";
        return jdbcTemplate.query(sql, questionRowMapper, category_id);
    }

    public int addQuestion(Question question){
        String sql = "INSERT INTO Question (category_id, description, is_active) VALUES (?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                question.getCategory_id(),
                question.getDescription(),
                question.getIs_active()
        );
    }

    public int updateQuestion(Question question){
        String sql = "UPDATE Question SET category_id = ?, description = ?, is_active = ? " +
                "WHERE question_id = ?";
        return jdbcTemplate.update(
                sql,
                question.getCategory_id(),
                question.getDescription(),
                question.getIs_active(),
                question.getQuestion_id()
        );
    }

    public int deleteQuestionById(Integer id){
        String sql = "DELETE FROM Question WHERE question_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
