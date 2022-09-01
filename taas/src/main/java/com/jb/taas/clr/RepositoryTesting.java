package com.jb.taas.clr;

import com.jb.taas.beans.ClientType;
import com.jb.taas.beans.Task;
import com.jb.taas.beans.User;
import com.jb.taas.repos.TaskRepository;
import com.jb.taas.repos.UserRepository;
import com.jb.taas.utils.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kobis on 12 May, 2022
 */
@Component
@RequiredArgsConstructor
@Order(1)
public class RepositoryTesting implements CommandLineRunner {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(Art.REPOSITORY_TESTING);
        System.out.println(Art.REPOSITORY_TESTING);


        User u2 = User.builder()
                .email("ariel@gmail.com")
                .password("1234")
                .type(ClientType.USER)
                .build();

        User u3 = User.builder()
                .email("bar@gmail.com")
                .password("1234")
                .type(ClientType.USER)
                .build();

        Task t1 = Task
                .builder()
                .group("Spring")
                .title("Cat & Toys Ex")
                .description("Spring Homework")
                .when(Timestamp.valueOf(LocalDateTime.now().plusWeeks(2)))
                .user(u2)
                .build();

        Task t2 = Task
                .builder()
                .group("Spring")
                .title("Author & Books Ex")
                .description("Spring Homework")
                .when(Timestamp.valueOf(LocalDateTime.now().plusWeeks(2)))
                .user(u2)
                .build();


        Task t3 = Task
                .builder()
                .group("Spring")
                .title("prepare for Spring Exam")
                .description("Spring Homework")
                .when(Timestamp.valueOf(LocalDateTime.of(2022,5,26,15,25)))
                .user(u3)
                .build();


        u2.setTasks(Arrays.asList(t1,t2));
        u3.setTasks(List.of(t3));

//        taskRepository.saveAll(Arrays.asList(t1,t2,t3));
//        taskRepository.findAll().forEach(System.out::println);
        userRepository.saveAll(Arrays.asList(u2,u3));
        userRepository.findAll().forEach(System.out::println);
    }
}
