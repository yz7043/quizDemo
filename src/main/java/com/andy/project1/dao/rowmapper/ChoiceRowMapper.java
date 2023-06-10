package com.andy.project1.dao.rowmapper;

import com.andy.project1.domain.Choice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ChoiceRowMapper implements RowMapper<Choice> {
    @Override
    public Choice mapRow(ResultSet rs, int rowNum) throws SQLException {
        Choice choice = new Choice();
        choice.setChoice_id(rs.getInt("choice_id"));
        choice.setQuestion_id(rs.getInt("question_id"));
        choice.setDescription(rs.getString("description"));
        choice.setIs_correct(rs.getBoolean("is_correct"));
        return choice;
    }
}
