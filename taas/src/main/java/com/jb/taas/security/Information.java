package com.jb.taas.security;

import com.jb.taas.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by kobis on 19 May, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Information {

    private int userId;
    private ClientType ClientType;
    private LocalDateTime time;
    private String email;
}
