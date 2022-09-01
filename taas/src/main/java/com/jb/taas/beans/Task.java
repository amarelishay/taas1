package com.jb.taas.beans;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * Created by kobis on 12 May, 2022
 */
@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false,name = "task_group")
    private String group;

    @Column(name = "task_when")
    private Date when;

    @ManyToOne
    @ToString.Exclude
    private User user;



}
