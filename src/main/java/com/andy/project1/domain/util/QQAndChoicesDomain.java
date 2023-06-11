package com.andy.project1.domain.util;

import com.andy.project1.domain.Choice;
import com.andy.project1.domain.Question;
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
public class QQAndChoicesDomain {
    private QuizQuestion quizQuestion;
    private List<Choice> choices;
    private Question question;

    public void removeCorrectChoice(){
        for(Choice choice : choices){
            choice.setIs_correct(false);
        }
    }
}
