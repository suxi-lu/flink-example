package com.learn.spring.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class VisMessageVO implements Serializable {

    private int userId;
    private int num;

}
