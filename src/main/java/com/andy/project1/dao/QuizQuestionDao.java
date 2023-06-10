package com.andy.project1.dao;

import com.andy.project1.dao.rowmapper.QuizQuestionMapper;
import com.andy.project1.domain.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizQuestionDao {
    private final JdbcTemplate jdbcTemplate;
    private final QuizQuestionMapper quizQuestionMapper;

    @Autowired
    public QuizQuestionDao(JdbcTemplate jdbcTemplate, QuizQuestionMapper quizQuestionMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.quizQuestionMapper = quizQuestionMapper;
    }

    public List<QuizQuestion> getQuizQuestionByQuizId(Integer quiz_id){
        String sql = "SELECT * FROM QuizQuestion WHERE quiz_id = ?";
        return jdbcTemplate.query(sql, quizQuestionMapper, quiz_id);
    }

    public QuizQuestion getQuizQuestionBy(Integer qq_id){
        String sql = "SELECT * FROM QuizQuestion WHERE qq_id = ?";
        List<QuizQuestion> result = jdbcTemplate.query(sql, quizQuestionMapper, qq_id);
        return result.size() == 0 ? null : result.get(0);
    }

    public int addQuizQuestion(QuizQuestion quizQuestion){
        String sql = "INSERT INTO QuizQuestion (quiz_id, question_id, user_choice_id) VALUES" +
                "(?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                quizQuestion.getQuiz_id(),
                quizQuestion.getQuestion_id(),
                quizQuestion.getUser_choice_id()
        );
    }

    public int updateQuizQuestion(QuizQuestion question){
        String sql = "UPDATE QuizQuestion SET quiz_id = ?, question_id = ?, user_choice_id = ?" +
                "WHERE qq_id = ?";
        return jdbcTemplate.update(
                sql,
                question.getQuiz_id(),
                question.getQuestion_id(),
                question.getUser_choice_id(),
                question.getQq_id()
        );
    }

    public int deleteQuizQuestionById(Integer qq_id){
        String sql = "DELETE FROm QuizQuestion WHERE qq_id = ?";
        return jdbcTemplate.update(sql, qq_id);
    }
}
