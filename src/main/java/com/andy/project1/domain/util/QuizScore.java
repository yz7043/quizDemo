package com.andy.project1.domain.util;

import com.andy.project1.domain.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class QuizScore {
    Quiz quiz;
    Float score;
}
