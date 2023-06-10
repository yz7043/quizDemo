package com.andy.project1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contact {
    private Integer contact_id;
    private String subject;
    private String message;
    private String email;
    private Timestamp time;
    public void setTime(Timestamp time) {
        Instant instant = time.toInstant();
        Instant truncatedInstant = instant.truncatedTo(ChronoUnit.SECONDS);
        this.time = Timestamp.from(truncatedInstant);
    }
}
