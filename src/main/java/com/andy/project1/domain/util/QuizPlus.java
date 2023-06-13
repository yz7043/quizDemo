package com.andy.project1.domain.util;

import com.andy.project1.domain.Category;
import com.andy.project1.domain.Choice;
import com.andy.project1.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class QuizPlus {
    private Category category;
    private List<Question> questionList;
    private List<List<Choice>> choices;
    private List<Integer> userChoices; //choice id
    private Integer curIdx;
}
