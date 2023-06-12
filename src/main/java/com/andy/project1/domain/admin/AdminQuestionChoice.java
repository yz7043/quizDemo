package com.andy.project1.domain.admin;

import com.andy.project1.domain.Category;
import com.andy.project1.domain.Choice;
import com.andy.project1.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AdminQuestionChoice{
    private Question question;
    private Category category;
    private List<Choice> choices;
}
