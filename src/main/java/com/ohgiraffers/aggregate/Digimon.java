package com.ohgiraffers.aggregate;

import java.io.Serializable;

/*
* 여기로 모여! 선택받은 아이들~
* 크크
*
* */

public class Digimon implements Serializable {
    private int id;
    private String name;
    private int level;
    private int feedGage;

    // 필기. 희망사항
//    private String type;

//
    public Digimon() {
    }

    public Digimon(int id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getFeedGage() {
        return feedGage;
    }

    public void setFeedGage(int feedGage) {
        this.feedGage = this.feedGage + feedGage;
        if (this.feedGage >= 100){
            this.feedGage = 100;
        }
    }

    @Override
    public String toString() {
        return "Digimon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", feedGage=" + feedGage +
                '}';
    }
}
