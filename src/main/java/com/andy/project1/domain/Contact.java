package com.andy.project1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private Integer contact_id;
    private String subject;
    private String message;
    private String email;
    private Timestamp timestamp;
}
