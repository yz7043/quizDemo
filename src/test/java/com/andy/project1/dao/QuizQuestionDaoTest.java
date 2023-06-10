package com.andy.project1.dao;

import com.andy.project1.dao.rowmapper.QuizQuestionMapper;
import com.andy.project1.domain.*;
import com.sun.javafx.geom.transform.GeneralTransform3D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class QuizQuestionDaoTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private QuizQuestionDao quizQuestionDao;
    @Autowired
    private QuizQuestionMapper quizQuestionMapper;

    QuizQuestion qq1;
    QuizQuestion qq2;

    Quiz quiz;
    Category category;
    Choice choice;
    Question question;
    User user;
    @BeforeEach
    public void setup(){
//        category = new Category(null, "cate");
//        KeyHolder cateKey = new GeneratedKeyHolder();
//        user = new User(null, "emailsdasdas", "sdsadpwd", "frist", "last", true, false);
//        KeyHolder userKey = new GeneratedKeyHolder();
//        quiz = new Quiz(null, )
    }

    @AfterEach
    public void after(){}

}
