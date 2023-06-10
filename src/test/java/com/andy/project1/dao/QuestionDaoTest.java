package com.andy.project1.dao;


import com.andy.project1.dao.rowmapper.QuestionRowMapper;
import com.andy.project1.domain.Category;
import com.andy.project1.domain.Question;
import org.apache.tomcat.jni.Time;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
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
public class QuestionDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private QuestionRowMapper questionRowMapper;
    @Autowired
    private QuestionDao questionDao;

    Category category;

    Question question1;
    Question question2;
    @BeforeEach
    public void setup(){
        category = new Category(null, "TestCate");
        KeyHolder cateKey = new GeneratedKeyHolder();
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
        String sql = "INSERT INTO Question (category_id, description, is_active) VALUES (?,?,?)";
        question1 = new Question(null, category.getCategory_id(), "q1", true);
        question2 = new Question(null, category.getCategory_id(), "q2", false);
        KeyHolder k1 = new GeneratedKeyHolder();
        KeyHolder k2 = new GeneratedKeyHolder();
        jdbcTemplate.update(
            con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, question1.getCategory_id());
                ps.setString(2, question1.getDescription());
                ps.setBoolean(3, question1.getIs_active());
                return ps;
            }, k1
        );
        question1.setQuestion_id(k1.getKey().intValue());

        jdbcTemplate.update(
            con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, question2.getCategory_id());
                ps.setString(2, question2.getDescription());
                ps.setBoolean(3, question2.getIs_active());
                return ps;
            }, k2
        );
        question2.setQuestion_id(k2.getKey().intValue());
    }

    @Test
    public void getQuestionByIdTest(){
        Question found = questionDao.getQuestionById(question1.getQuestion_id());
        assertThat(found.toString()).isEqualTo(question1.toString());
    }

    @Test
    public void getAllQuestionsTest(){
        List<Question> allQuestions = questionDao.getAllQuestions();
        assertThat(allQuestions).extracting(Question::toString).containsExactlyInAnyOrder(
                question1.toString(), question2.toString()
        );
    }

    @Test
    public void getQuestionByCategoryTest(){
        List<Question> found = questionDao.getQuestionsByCategory(category.getCategory_id());
        List<Question> foundNull = questionDao.getQuestionsByCategory(-1);
        assertThat(found).extracting(Question::toString).containsExactlyInAnyOrder(
                question1.toString(), question2.toString()
        );
        assertThat(foundNull.size()).isEqualTo(0);
    }

    @Test
    public void addQuestionTest(){
        Question question = new Question(null, category.getCategory_id(), "add", true);
        questionDao.addQuestion(question);
        List<Question> found = jdbcTemplate.query("SELECT * FROM question", questionRowMapper);
        assertThat(found.size()).isEqualTo(3);
        for(Question q : found){
            if(q.getQuestion_id() != question1.getQuestion_id() &&
                q.getQuestion_id() != question2.getQuestion_id()){
                question.setQuestion_id(q.getQuestion_id());
                break;
            }
        }
        assertThat(found).extracting(Question::toString).containsExactlyInAnyOrder(
                question1.toString(), question2.toString(), question.toString()
        );
        jdbcTemplate.update("DELETE FROM question where question_id = ?", question.getQuestion_id());
    }

    @Test
    public void updateQuestion(){
        question1.setDescription("sksksk");
        question1.setIs_active(false);
        int res = questionDao.updateQuestion(question1);
        assertThat(res).isEqualTo(1);
        List<Question> query = jdbcTemplate.query("SELECT * FROM Question WHERE question_id = ?", questionRowMapper,
                question1.getQuestion_id());
        assertThat(query.size()).isEqualTo(1);
        assertThat(query).extracting(Question::toString).contains(question1.toString());
    }

    @Test
    public void deleteQuestionByIDTest(){
        questionDao.deleteQuestionById(question1.getQuestion_id());
        List<Question> query = jdbcTemplate.query("SELECT * FROM Question WHERE question_id = ?", questionRowMapper,
                question1.getQuestion_id());
        assertThat(query.size()).isEqualTo(0);
    }

    @AfterEach
    public void after(){
        String sql = "DELETE FROM Question WHERE question_id = ?";
        jdbcTemplate.update(sql, question1.getQuestion_id());
        jdbcTemplate.update(sql, question2.getQuestion_id());
        jdbcTemplate.update("DELETE FROM category WHERE category_id = ?", category.getCategory_id());
    }
}
