package com.andy.project1.dao;

import com.andy.project1.dao.rowmapper.CategoryRowMapper;
import com.andy.project1.dao.rowmapper.ChoiceRowMapper;
import com.andy.project1.dao.rowmapper.QuestionRowMapper;
import com.andy.project1.domain.Category;
import com.andy.project1.domain.Choice;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
import com.andy.project1.domain.Question;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ChoiceDaoTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ChoiceDao choiceDao;
    @Autowired
    private ChoiceRowMapper choiceRowMapper;

    Category category;
    Question question;
    Choice choice1;
    Choice choice2;
    @BeforeEach
    public void setup(){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        category = new Category(null, "TestCategoryLLL");
        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement("INSERT INTO Category (name) values (?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, category.getName());
                    return ps;
                }, keyHolder
        );
        category.setCategory_id(keyHolder.getKey().intValue());
        KeyHolder keyHolderQ = new GeneratedKeyHolder();
        question = new Question(null, category.getCategory_id(), "Description", true);
        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO Question (category_id, description, is_active) VALUES (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, question.getCategory_id());
                    ps.setString(2, question.getDescription());
                    ps.setBoolean(3, question.getIs_active());
                    return ps;
                }, keyHolderQ
        );
        question.setQuestion_id(keyHolderQ.getKey().intValue());

        //
        KeyHolder c1 = new GeneratedKeyHolder();
        KeyHolder c2 = new GeneratedKeyHolder();
        choice1 = new Choice(null, question.getQuestion_id(), "des1", true);
        choice2 = new Choice(null, question.getQuestion_id(), "des2", false);
        String sql = "INSERT INTO Choice (question_id, description, is_correct) VALUES (?,?,?)";
        jdbcTemplate.update(
            con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, choice1.getQuestion_id());
                ps.setString(2, choice1.getDescription());
                ps.setBoolean(3, choice1.getIs_correct());
                return ps;
            }, c1
        );
        choice1.setChoice_id(c1.getKey().intValue());
        jdbcTemplate.update(
            con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, choice2.getQuestion_id());
                ps.setString(2, choice2.getDescription());
                ps.setBoolean(3, choice2.getIs_correct());
                return ps;
            }, c2
        );
        choice2.setChoice_id(c2.getKey().intValue());
        System.out.println("+++++++++++++++++++");
        System.out.println(choice1);
        System.out.println(choice2);
        System.out.println("+++++++++++++++++++");
    }

    @Test
    public void getChoiceByIdTest(){
        Choice choice = choiceDao.getChoiceById(choice1.getChoice_id());
        assertThat(choice.toString()).isEqualTo(choice1.toString());
    }

    @Test
    public void getChoicesByQuestionIdTest(){
        List<Choice> result = choiceDao.getChoicesByQuestionId(question.getQuestion_id());
        assertThat(result).isNotNull();
        assertThat(result).extracting(Choice::toString).containsExactlyInAnyOrder(
                choice1.toString(), choice2.toString()
        );
    }

    @Test
    public void addChoiceTest(){
        Choice choice = new Choice(null, question.getQuestion_id(), "DDSdsad", false);
        int res = choiceDao.addChoice(choice);
        assertThat(res).isEqualTo(1);
        List<Choice> result = jdbcTemplate.query("SELECT * FROM Choice", choiceRowMapper);
        assertThat(result.size()).isEqualTo(3);

        assertThat(result)
            .filteredOn(r -> r.getChoice_id() != choice1.getChoice_id() && r.getChoice_id() != choice2.getChoice_id())
            .extracting(Choice::getQuestion_id).contains(choice.getQuestion_id());
        assertThat(result)
            .filteredOn(r -> r.getChoice_id() != choice1.getChoice_id() && r.getChoice_id() != choice2.getChoice_id())
            .extracting(Choice::getDescription).contains(choice.getDescription());
        assertThat(result)
            .filteredOn(r -> r.getChoice_id() != choice1.getChoice_id() && r.getChoice_id() != choice2.getChoice_id())
            .extracting(Choice::getIs_correct).contains(choice.getIs_correct());
        for(Choice c : result){
            if(c.getChoice_id() != choice1.getChoice_id() && c.getChoice_id() != choice2.getChoice_id()){
                jdbcTemplate.update("DELETE FROM Choice where choice_id = ?", c.getChoice_id());
                break;
            }
        }

    }

    @Test
    public void updateChoiceTest(){
        choice1.setDescription("modified");
        choice1.setIs_correct(false);
        int res = choiceDao.updateChoice(choice1);
        assertThat(res).isEqualTo(1);
        Choice result = jdbcTemplate.query("SELECT * FROM Choice WHERE choice_id = ?",
                choiceRowMapper,
                choice1.getChoice_id()).get(0);
        assertThat(result.toString()).isEqualTo(choice1.toString());
    }

    @Test
    public void deleteChoiceByIdTest(){
        int res = choiceDao.deleteChoiceById(choice1.getChoice_id());
        assertThat(res).isEqualTo(1);
        List<Choice> choices = jdbcTemplate.query("SELECT * FROM Choice", choiceRowMapper);
        assertThat(choices.size()).isEqualTo(1);
        assertThat(choices).extracting(Choice::getChoice_id).doesNotContain(choice1.getChoice_id());
    }

    @AfterEach
    public void after(){
        String sql = "DELETE FROM Choice WHERE choice_id = ?";
        jdbcTemplate.update(sql, choice1.getChoice_id());
        jdbcTemplate.update(sql, choice2.getChoice_id());

        String sqlQuestion = "DELETE FROM Question WHERE question_id = ?";
        jdbcTemplate.update(sqlQuestion, question.getQuestion_id());
        String sqlCategory = "DELETE FROM Category WHERE category_id = ?";
        jdbcTemplate.update(sqlCategory, category.getCategory_id());
    }

}
