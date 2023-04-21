package com.runescape.entity.model;

public class AABB {
    int xMid;
    int yMid;
    int zMid;
    int xMidOffset;
    int yMidOffset;
    int zMidOffset;

    AABB(int xMid, int yMid, int zMid, int xMidOffset, int yMidOffset, int zMidOffset) {
        this.xMid = xMid;
        this.yMid = yMid;
        this.zMid = zMid;
        this.xMidOffset = xMidOffset;
        this.yMidOffset = yMidOffset;
        this.zMidOffset = zMidOffset;
    }

}