package com.ohgiraffers.aggregate;

public enum Motion {
    Left(1),
    Right(1),
    Upward(1),
    Downward(1);

    private final int point;

    Motion(int point) {
        this.point = point;
    }

    public int getFatigue() {
        return point;
    }

}
