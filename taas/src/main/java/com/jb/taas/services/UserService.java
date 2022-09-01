package com.jb.taas.services;

import com.jb.taas.dto.TaskDto;
import com.jb.taas.exceptions.TaskSystemException;

import java.util.List;

/**
 * Created by kobis on 19 May, 2022
 */
public interface UserService {

    List<TaskDto> getAllTasks(int userId);
    void addTask(int userId,TaskDto taskDto) throws TaskSystemException;

    boolean login(String email, String password);
}
