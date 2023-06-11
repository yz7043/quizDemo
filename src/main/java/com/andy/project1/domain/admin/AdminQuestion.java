package com.andy.project1.domain.admin;

import com.andy.project1.domain.Category;
import com.andy.project1.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AdminQuestion {
    private Question question;
    private Category category;
}
