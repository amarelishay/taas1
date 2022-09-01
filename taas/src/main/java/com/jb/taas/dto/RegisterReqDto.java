package com.jb.taas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by kobis on 19 May, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterReqDto {

    @Email
    private String email;

    @Length(min = 3,max = 8)
    private String password;
}
