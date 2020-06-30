package com.learn.message.vo;

import java.io.Serializable;

public class VisMessageVO implements Serializable {

    private int userId;
    private int num;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "VisMessageVO{" +
                "userId=" + userId +
                ", num=" + num +
                '}';
    }
}
