package com.andy.project1.dao;

import com.andy.project1.dao.rowmapper.QuizRowMapper;
import com.andy.project1.domain.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class QuizDao {
    private final JdbcTemplate jdbcTemplate;
    private final QuizRowMapper quizRowMapper;

    @Autowired
    public QuizDao(JdbcTemplate jdbcTemplate, QuizRowMapper quizRowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.quizRowMapper = quizRowMapper;
    }

    public List<Quiz> getAllQuiz(){
        String query = "SELECT * FROM Quiz";
        return jdbcTemplate.query(query, quizRowMapper);
    }

    public Quiz getQuizById(Integer id){
        String query = "SELECT * FROM Quiz WHERE quiz_id = ?";
        List<Quiz> quizzes = jdbcTemplate.query(query, quizRowMapper, id);
        return quizzes.size() == 0 ? null : quizzes.get(0);
    }

    public List<Quiz> getQuizByCategoryId(Integer id){
        String query = "SELECT * FROM Quiz WHERE category_id = ?";
        return jdbcTemplate.query(query, quizRowMapper, id);
    }

    @Deprecated
    public int addQuiz(Quiz quiz){
        String sql = "INSERT INTO Quiz (user_id, category_id, name, time_start, time_end) VALUES" +
                "(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                quiz.getUser_id(),
                quiz.getCategory_id(),
                quiz.getName(),
                quiz.getTime_start(),
                quiz.getTime_end()
        );
    }

    public int addQuizAndGetID(Quiz quiz){
        String sql = "INSERT INTO Quiz (user_id, category_id, name, time_start, time_end) VALUES" +
                "(?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
            con -> {
                PreparedStatement ps = con.prepareStatement(
                        sql, Statement.RETURN_GENERATED_KEYS
                );
                ps.setInt(1, quiz.getUser_id());
                ps.setInt(2, quiz.getCategory_id());
                ps.setString(3, quiz.getName());
                ps.setTimestamp(4, quiz.getTime_start());
                ps.setTimestamp(5, quiz.getTime_end());
                return ps;
            }, keyHolder
        );
        return keyHolder.getKey().intValue();
    }


    public int updateQuiz(Quiz quiz){
        String sql = "UPDATE Quiz SET user_id = ?, category_id = ?, name = ?, time_start = ?, time_end = ?" +
                "WHERE quiz_id = ?";
        return jdbcTemplate.update(
                sql,
                quiz.getUser_id(),
                quiz.getCategory_id(),
                quiz.getName(),
                quiz.getTime_start(),
                quiz.getTime_end(),
                quiz.getQuiz_id()
        );
    }

    public int deleteQuizById(Integer id){
        String sql = "DELETE FROM Quiz WHERE quiz_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<Quiz> getQuizByUserId(Integer id){
        String sql = "SELECT * FROM Quiz WHERE user_id = ?";
        return jdbcTemplate.query(sql, quizRowMapper, id);
    }
}
