package com.jb.taas.dto;

import com.jb.taas.beans.ClientType;
import com.jb.taas.beans.Task;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kobis on 19 May, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {


    @Min(0)
    private int id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    @NotBlank
    private String type;

    @Singular
    private List<TaskDto> tasks = new ArrayList<>();

}
