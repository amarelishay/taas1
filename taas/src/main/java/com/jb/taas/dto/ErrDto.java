package com.jb.taas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by kobis on 12 May, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrDto {

    private final String key = "TaaS";
    private String value;
}
