package com.andy.project1.domain;

import com.andy.project1.util.TimestampHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@ToString
public class Contact {
    private Integer contact_id;
    private String subject;
    private String message;
    private String email;
    private Timestamp time;
    public void setTime(Timestamp time) {
        if(time == null) return;
        Instant instant = time.toInstant();
        Instant truncatedInstant = instant.truncatedTo(ChronoUnit.SECONDS);
        this.time = Timestamp.from(truncatedInstant);
    }

    public Contact(Integer contact_id, String subject, String message, String email, Timestamp time) {
        this.contact_id = contact_id;
        this.subject = subject;
        this.message = message;
        this.email = email;
        Instant instant = time.toInstant();
        this.time = Timestamp.from(instant.truncatedTo(ChronoUnit.SECONDS));
    }

    public String getTimeFormatString(){
        return TimestampHelper.timeStampToFormatData(time);
    }

    public static final Integer SUBJECT_MAX_LEN = 50;
    public static final Integer MSG_MAX_LEN = 2000;
    public static final Integer EMAIL_MAX_LEN = 50;
}
