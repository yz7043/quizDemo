package com.andy.project1.dao;

import com.andy.project1.dao.rowmapper.QuizRowMapper;
import com.andy.project1.domain.Category;
import com.andy.project1.domain.Quiz;
import com.andy.project1.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QuizDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuizRowMapper quizRowMapper;

    Quiz quiz1;
    Quiz quiz2;

    Category category;
    User user;

    @BeforeEach
    public void setup(){
        user = new User(null, "testQuiz@mail", "pwd", "testMailFirst",
                "testMailLast", true, false);
        category = new Category(null, "TestCate");
        KeyHolder userKey = new GeneratedKeyHolder();
        KeyHolder cateKey = new GeneratedKeyHolder();
        jdbcTemplate.update(
            con -> {
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Users (email, password, firstname, lastname, is_active, is_admin)" +
                            "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFirstname());
                ps.setString(4, user.getLastname());
                ps.setBoolean(5, user.getIs_active());
                ps.setBoolean(6, user.getIs_admin());
                return ps;
            }, userKey
        );
        user.setUser_id(userKey.getKey().intValue());
        jdbcTemplate.update(
            con -> {
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO category (name) values (?)", Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, category.getName());
                return ps;
            }, cateKey
        );
        category.setCategory_id(cateKey.getKey().intValue());
        quiz1 = new Quiz(null,
                user.getUser_id(),
                category.getCategory_id(),
                "q1",
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()));
        quiz2 = new Quiz(null,
                user.getUser_id(),
                category.getCategory_id(),
                "q2",
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()));
        String sql = "INSERT INTO Quiz (user_id, category_id, name, time_start, time_end) VALUES (?,?,?,?,?)";
        KeyHolder q1Key = new GeneratedKeyHolder();
        jdbcTemplate.update(
            con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, quiz1.getUser_id());
                ps.setInt(2, quiz1.getCategory_id());
                ps.setString(3, quiz1.getName());
                ps.setTimestamp(4, quiz1.getTime_start());
                ps.setTimestamp(5, quiz1.getTime_end());
                return ps;
            }, q1Key
        );
        quiz1.setQuiz_id(q1Key.getKey().intValue());

        KeyHolder q2Key = new GeneratedKeyHolder();
        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, quiz2.getUser_id());
                    ps.setInt(2, quiz2.getCategory_id());
                    ps.setString(3, quiz2.getName());
                    ps.setTimestamp(4, quiz2.getTime_start());
                    ps.setTimestamp(5, quiz2.getTime_end());
                    return ps;
                }, q2Key
        );
        quiz2.setQuiz_id(q2Key.getKey().intValue());
    }

    private List<Quiz> getAllQuizByJDBC(){
        return jdbcTemplate.query("SELECT * FROM Quiz", quizRowMapper);
    }


    @Test
    public void getAllQuizTest(){
        List<Quiz> found = quizDao.getAllQuiz();
        assertThat(found.size()).isEqualTo(2);
        assertThat(found).extracting(Quiz::toString).containsExactlyInAnyOrder(
            quiz1.toString(), quiz2.toString()
        );
    }

    @Test
    public void getQuizByIdTest(){
        Quiz found = quizDao.getQuizById(quiz1.getQuiz_id());
        assertThat(found.toString()).isEqualTo(quiz1.toString());
        Quiz foundNull = quizDao.getQuizById(-1);
        assertThat(foundNull).isNull();
    }

    @Test
    public void addQuizTest(){
        Quiz quiz = new Quiz(null, user.getUser_id(), category.getCategory_id(), "add",
                Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        quizDao.addQuiz(quiz);
        List<Quiz> found = getAllQuizByJDBC();
        assertThat(found.size()).isEqualTo(3);
        for(Quiz q : found){
            if(q.getQuiz_id() != quiz1.getQuiz_id() && q.getQuiz_id() != quiz2.getQuiz_id()){
                quiz.setQuiz_id(q.getQuiz_id());
                assertThat(quiz.toString()).isEqualTo(q.toString());
                break;
            }
        }
        jdbcTemplate.update("DELETE FROM Quiz where quiz_id = ?", quiz.getQuiz_id());
    }


    @Test
    public void updateQuizTest(){
        quiz1.setName("lslsl");
        quiz1.setTime_start(Timestamp.from(Instant.now()));
        quiz1.setTime_end(Timestamp.from(Instant.now()));
        quizDao.updateQuiz(quiz1);
        Quiz q = jdbcTemplate.query("SELECT * FROM Quiz WHERE quiz_id = ?", quizRowMapper, quiz1.getQuiz_id()).get(0);
        assertThat(q.toString()).isEqualTo(quiz1.toString());
    }

    @Test
    public void deleteQuizByIdTest(){
        quizDao.deleteQuizById(quiz1.getQuiz_id());
        List<Quiz> found = getAllQuizByJDBC();
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).toString()).isEqualTo(quiz2.toString());
    }


    @AfterEach
    public void after(){
        String sql = "DELETE FROM Quiz WHERE quiz_id = ?";
        jdbcTemplate.update(sql, quiz1.getQuiz_id());
        jdbcTemplate.update(sql, quiz2.getQuiz_id());
        jdbcTemplate.update("DELETE FROM users WHERE user_id = ?", user.getUser_id());
        jdbcTemplate.update("DELETE FROM category WHERE category_id = ?", category.getCategory_id());
    }
}
