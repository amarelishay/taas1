package com.jb.taas.exceptions;

/**
 * Created by kobis on 12 May, 2022
 */
public class TaskSystemException extends Exception{


    public TaskSystemException(ErrMsg errMsg) {
        super(errMsg.getMsg());
    }
}
