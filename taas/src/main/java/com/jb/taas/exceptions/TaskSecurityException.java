package com.jb.taas.exceptions;

import lombok.Data;

/**
 * Created by kobis on 19 May, 2022
 */
@Data
public class TaskSecurityException extends Exception{

    private SecMsg secMsg;
    public TaskSecurityException(SecMsg secMsg){
        super(secMsg.getMsg());
        this.secMsg = secMsg;
    }
}
