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
public class Quiz {
    private Integer quiz_id;
    private Integer user_id;
    private Integer category_id;
    private String name;
    private Timestamp time_start;
    private Timestamp time_end;

    public void setTime_start(Timestamp time){
        Instant instant = time.toInstant();
        Instant truncatedInstant = instant.truncatedTo(ChronoUnit.SECONDS);
        this.time_start = Timestamp.from(truncatedInstant);
    }

    public void setTime_end(Timestamp time){
        Instant instant = time.toInstant();
        Instant truncatedInstant = instant.truncatedTo(ChronoUnit.SECONDS);
        this.time_start = Timestamp.from(truncatedInstant);
    }
}
