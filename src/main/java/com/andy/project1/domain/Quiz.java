package com.andy.project1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    private Integer quiz_id;
    private Integer user_id;
    private Integer category_id;
    private String name;
    private Timestamp time_start;
    private Timestamp time_end;
}
