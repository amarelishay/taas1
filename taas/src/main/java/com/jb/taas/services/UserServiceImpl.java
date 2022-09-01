package com.jb.taas.services;

import com.jb.taas.beans.Task;
import com.jb.taas.beans.User;
import com.jb.taas.dto.TaskDto;
import com.jb.taas.exceptions.ErrMsg;
import com.jb.taas.exceptions.TaskSystemException;
import com.jb.taas.mapper.TaskMapper;
import com.jb.taas.repos.TaskRepository;
import com.jb.taas.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kobis on 19 May, 2022
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDto> getAllTasks(int userId) {
        return taskMapper.toDtoList(taskRepository.findByUserId(userId));
    }

    @Override
    public void addTask(int userId,TaskDto taskDto) throws TaskSystemException {
        Task toAdd = taskMapper.toDao(taskDto);
        User user = userRepository.findById(userId).orElseThrow(()->new TaskSystemException(ErrMsg.ID_NOT_EXIST));
        toAdd.setUser(user);

        taskRepository.save(toAdd);
    }

    @Override
    public boolean login(String email, String password) {
        if(userRepository.existsByEmailAndPassword(email, password)){
            return true;
        }
        return false;
    }
}
