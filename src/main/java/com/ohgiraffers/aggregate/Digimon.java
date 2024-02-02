package com.ohgiraffers.aggregate;

import java.io.Serializable;

/*
* 여기로 모여! 선택받은 아이들~
* 크크
*
* */

public class Digimon implements Serializable {
    public static final int MIN_GAGE = 0;
    public static final int DEFAULT_GAGE = 30;
    public static final int MAX_GAGE = 100;
    public static final int STAMINA_RECOVERY_GAGE = 50;

    private int id;
    private String name;
    private int level;
    private int feedGage = DEFAULT_GAGE;
    private int staminaGage = DEFAULT_GAGE;
    private int fatigueGage;

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

    public int getStaminaGage() {
        return staminaGage;
    }

    public void setStaminaGage(int gageValue) {
        this.staminaGage = gageValue;
    }

    public void setFatigueGage(int fatigueGage) {
        this.fatigueGage -= fatigueGage;        // this.fatigueGage = this.fatigueGage - fatigueGage;
        if (this.fatigueGage <= 0){
            this.fatigueGage = 0;
        }
    }

    @Override
    public String toString() {
        return "Digimon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", feedGage=" + feedGage +
                ", staminaGage=" + staminaGage +
                ", fatigueGage=" + fatigueGage +
                '}';
    }
}
