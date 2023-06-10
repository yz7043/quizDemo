package com.andy.project1.dao;

import com.andy.project1.dao.rowmapper.ChoiceRowMapper;
import com.andy.project1.domain.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChoiceDao {
    private final JdbcTemplate jdbcTemplate;
    private final ChoiceRowMapper choiceRowMapper;


    @Autowired
    public ChoiceDao(JdbcTemplate jdbcTemplate, ChoiceRowMapper choiceRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.choiceRowMapper = choiceRowMapper;
    }

    public Choice getChoiceById(Integer id){
        String sql = "SELECT * FROM Choice WHERE choice_id = ?";
        List<Choice> result = jdbcTemplate.query(sql, choiceRowMapper, id);
        return result.size() == 0 ? null : result.get(0);
    }

    public List<Choice> getChoicesByQuestionId(Integer questionId){
        String sql = "SELECT * FROM Choice WHERE question_id = ?";
        return jdbcTemplate.query(sql, choiceRowMapper, questionId);
    }

    public int addChoice(Choice choice){
         String sql = "INSERT INTO Choice (question_id, description, is_correct) VALUES (?, ?, ?)";
         return jdbcTemplate.update(
                 sql,
                 choice.getQuestion_id(),
                 choice.getDescription(),
                 choice.getIs_correct()
         );
    }

    public int updateChoice(Choice choice){
        String sql = "UPDATE Choice SET question_id = ?, description = ?, is_correct = ? WHERE choice_id = ?";
        return jdbcTemplate.update(
                sql,
                choice.getQuestion_id(),
                choice.getDescription(),
                choice.getIs_correct(),
                choice.getChoice_id()
        );
    }

    public int deleteChoiceById(Integer id){
        String sql = "DELETE FROM Choice WHERE choice_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
