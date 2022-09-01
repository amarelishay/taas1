package com.jb.taas.services;

import com.jb.taas.beans.Task;
import com.jb.taas.dto.TaskDto;
import com.jb.taas.dto.UserDto;
import com.jb.taas.mapper.TaskMapper;
import com.jb.taas.mapper.UserMapper;
import com.jb.taas.repos.TaskRepository;
import com.jb.taas.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kobis on 19 May, 2022
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    private final UserMapper userMapper;
    @Override
    public int countUsers() {
        return (int) userRepository.count();
    }

    @Override
    public int countTasks() {
        return (int) taskRepository.count();
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskMapper.toDtoList(taskRepository.findAll());
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public boolean login(String email, String password)  {
        if (!(email.equals("admin@admin.com") || !password.equals("admin"))){
            return false;
        }
        return true;
    }
}
