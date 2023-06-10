package com.andy.project1.domain.util;

import com.andy.project1.domain.Question;
import com.andy.project1.domain.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class QuizQuestionDomain {
    Quiz quiz;
    List<QQAndChoicesDomain> questions;
}
