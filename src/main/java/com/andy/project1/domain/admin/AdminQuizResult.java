package com.andy.project1.domain.admin;

import com.andy.project1.domain.Category;
import com.andy.project1.domain.Choice;
import com.andy.project1.domain.Quiz;
import com.andy.project1.domain.QuizQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AdminQuizResult {
    private Quiz quiz;
    private AdminUser user;
    private List<QuizQuestion> quizQuestions;
    private List<List<Choice>> choices;
    private Integer score;
    private Category category;
}
