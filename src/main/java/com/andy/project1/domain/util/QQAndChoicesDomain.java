package com.andy.project1.domain.util;

import com.andy.project1.domain.Choice;
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
    private QuizQuestion question;
    private List<Choice> choices;
}
