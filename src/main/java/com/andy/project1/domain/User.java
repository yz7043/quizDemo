package com.andy.project1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer user_id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private Boolean is_active;
    private Boolean is_admin;
}
