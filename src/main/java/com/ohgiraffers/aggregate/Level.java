package com.ohgiraffers.aggregate;

public enum Level {
    BABY("유년기"),
    CHILD("성장기"),
    ADULT("성숙기"),
    PERFECT("완전체"),
    ULTIMATE("궁극체");

    private final String description;

    Level(String description) {
        this.description = description;
    }

    private int getOrdinal(String str){
        return 0;
    }
}

