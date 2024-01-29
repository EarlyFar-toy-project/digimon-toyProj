package com.ohgiraffers.aggregate;

public enum Food {
    Meat(1),
    Fish(2),
    Vegetable(1),
    Pill(5),
    SuperFood(10);

    private final int point;

    Food(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}
