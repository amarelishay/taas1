package com.jb.taas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Created by kobis on 12 May, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatesBetweenReq {

    private Timestamp start;
    private Timestamp end;
}
