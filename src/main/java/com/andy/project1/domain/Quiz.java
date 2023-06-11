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
        if(time == null)
            this.time_end = null;
        else{
            Instant instant = time.toInstant();
            Instant truncatedInstant = instant.truncatedTo(ChronoUnit.SECONDS);
            this.time_end = Timestamp.from(truncatedInstant);
        }
    }

    public Quiz(Integer quiz_id, Integer user_id, Integer category_id, String name, Timestamp time_start, Timestamp time_end) {
        this.quiz_id = quiz_id;
        this.user_id = user_id;
        this.category_id = category_id;
        this.name = name;
        Instant instant = time_start.toInstant();
        this.time_start = Timestamp.from(instant.truncatedTo(ChronoUnit.SECONDS));
        instant = time_end.toInstant();
        this.time_end = Timestamp.from(instant.truncatedTo(ChronoUnit.SECONDS));
    }

    public String getTimeStartFormatString(){
        return TimestampHelper.timeStampToFormatData(this.time_start);
    }

    public String getTimeEndFormatString(){
        return TimestampHelper.timeStampToFormatData(this.time_end);
    }
}
