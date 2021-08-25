package com.bawp.todoister.util;

import androidx.room.TypeConverter;

import com.bawp.todoister.model.Priority;

import java.util.Date;

public class Convertor {
    @TypeConverter
    public static Date formTimeStamp(Long value){
        return value == null ? null: new Date(value);
    }
    @TypeConverter
    public static Long formTimeStamp(Date date){
        return date == null ? null: date.getTime();
    }
    @TypeConverter
    public static Priority formTimeStamp(String value){
        return value == null ? null: Priority.valueOf(value);
    }
    @TypeConverter
    public static String formTimeStamp(Priority priority){
        return priority == null ? null: priority.name();
    }
}
