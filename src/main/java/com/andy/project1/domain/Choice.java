package com.andy.project1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Choice {
    private Integer choice_id;
    private Integer question_id;
    private String description;
    private Boolean is_correct;
}
