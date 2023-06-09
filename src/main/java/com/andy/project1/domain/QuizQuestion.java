package com.andy.project1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestion {
    private Integer qq_id;
    private Integer quiz_id;
    private Integer question_id;
    private Integer user_choice_id;
}
