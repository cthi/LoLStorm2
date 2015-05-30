package com.jclolstorm.lolstorm.utils;

public class DateUtils {

    public static String getTimeElapsed(long createdTime) {
        long elapsedTime = System.currentTimeMillis() - createdTime;
        long dayTime = elapsedTime / 86400000L;

        if (dayTime < 1) {
            long hours = (elapsedTime % 86400000L) / 3600000L;
            if (hours != 0) {
                return Long.toString(hours) + " hours ago";
            } else {
                return Integer.toString(1) + " hour ago";
            }
        } else {
            if (dayTime != 1) {
                return Long.toString(dayTime) + " days ago";
            } else {
                return Integer.toString(1) + " day ago";
            }
        }
    }
}
