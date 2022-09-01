package com.jb.taas.services;

import com.jb.taas.beans.Task;
import com.jb.taas.dto.TaskDto;
import com.jb.taas.dto.UserDto;

import java.util.List;

/**
 * Created by kobis on 19 May, 2022
 */
public interface AdminService {

    int countUsers();
    int countTasks();
    List<TaskDto> getAllTasks();
    List<UserDto> getAllUsers();

    boolean login(String email, String password);
}
