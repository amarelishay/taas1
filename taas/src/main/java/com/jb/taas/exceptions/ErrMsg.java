package com.jb.taas.exceptions;

import lombok.Getter;

/**
 * Created by kobis on 12 May, 2022
 */
@Getter
public enum ErrMsg {

    ID_NOT_EXIST("Id not found"),
    ID_ALREADY_EXIST("Id already exist"),
    INVALID_DATES("start date must be before end date");

    private String msg;

    ErrMsg(String msg){
        this.msg = msg;
    }
}
