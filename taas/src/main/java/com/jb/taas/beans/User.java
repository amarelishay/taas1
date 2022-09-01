package com.jb.taas.beans;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kobis on 19 May, 2022
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ClientType type;

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "user")
    @Singular
    private List<Task> tasks = new ArrayList<>();
}
