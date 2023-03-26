package com.example.learningspringboot.model;

public class Holiday {
    private final String day;
    private final String reason;
    private final Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    };

    public Holiday(String day, String season, Type type) {
        this.day = day;
        this.reason = season;
        this.type = type;
    }

    public String getDay() {
        return this.day;
    }


    public String getReason() {
        return this.reason;
    }


    public Type getType() {
        return this.type;
    }
}

   

