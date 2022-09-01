package com.jb.taas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by kobis on 12 May, 2022
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskPayloadDto {

    @NotBlank
    private String caption;

    @NotBlank
    private String info;

    @NotBlank
    private String classification;


    private Date dueDate;
}