package com.andy.project1.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampHelper {
    public static String timeStampToFormatData(Timestamp timestamp){
        if(timestamp == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = sdf.format(timestamp);
        return formattedTimestamp;
    }
}
