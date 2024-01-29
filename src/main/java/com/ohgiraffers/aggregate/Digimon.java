package com.ohgiraffers.aggregate;

import java.io.Serializable;

public class Digimon implements Serializable {
    private int id;
    private String name;
    private int level;
    private int feedGage;

    // 필기. 희망사항
//    private String type;


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
        this.feedGage = feedGage;
    }

    @Override
    public String toString() {
        return "MyDigimon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", feedGage=" + feedGage +
                '}';
    }
}
