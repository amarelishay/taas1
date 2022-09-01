package com.jb.taas.dto;

import com.jb.taas.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by kobis on 19 May, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResDto {
    private ClientType clientType;
    private String email;
    private UUID token;
}
