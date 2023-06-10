package com.andy.project1.dao;

import com.andy.project1.dao.rowmapper.CategoryRowMapper;
import com.andy.project1.domain.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CategoryDaoTest {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CategoryRowMapper categoryRowMapper;

    private static String name1 = "TestCategory1";
    private static String name2 = "TestCategory2";
    private Category category1;
    private Category category2 = new Category();
    @BeforeEach
    public void setup(){
        category1 = new Category();
        category2 = new Category();
        category1.setName(name1);
        category2.setName(name2);
        String sql = "INSERT INTO Category (name) value (?)";
        jdbcTemplate.update(sql, category1.getName());
        jdbcTemplate.update(sql, category2.getName());
    }

    @Test
    public void getAllCategories(){
        List<Category> result = categoryDao.getAllCategories();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).extracting(Category::getName).containsExactlyInAnyOrder(
                category1.getName(), category2.getName()
        );
    }

    @Test
    public void addCategoryTest(){
        Category category = new Category();
        category.setName("Test");
        int result = categoryDao.addCategory(category);
        assertThat(result).isEqualTo(1);

        String sql = "SELECT * FROM Category";
        List<Category> categories = jdbcTemplate.query(sql, categoryRowMapper);
        assertThat(categories).isNotNull();
        assertThat(categories.size()).isEqualTo(3);
        assertThat(categories).extracting(Category::getName).containsExactlyInAnyOrder(
                category1.getName(), category2.getName(), category.getName()
        );
        String sql1 = "DELETE FROM Category WHERE name = ?";
        int result1 = jdbcTemplate.update(sql1, category.getName());
        assertThat(result1).isEqualTo(1);
    }

    @Test
    public void deleteCategoryByIdTest(){
        String sql = "SELECT * FROM Category";
        List<Category> categories = jdbcTemplate.query(sql, categoryRowMapper);
        int result = categoryDao.deleteCategoryById(categories.get(0).getCategory_id());
        assertThat(result).isEqualTo(1);
        List<Category> categories1 = jdbcTemplate.query(sql, categoryRowMapper);
        assertThat(categories1.size()).isEqualTo(1);
        assertThat(categories1).extracting(Category::getName).containsExactlyInAnyOrder(categories.get(1).getName());
        assertThat(categories1).extracting(Category::getName).doesNotContain(categories.get(0).getName());
    }

    @AfterEach
    public void cleanup(){
        String sql = "DELETE FROM Category WHERE name = ?";
        jdbcTemplate.update(sql, category1.getName());
        jdbcTemplate.update(sql, category2.getName());
    }
}
