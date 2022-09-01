package com.jb.taas.services;

import com.jb.taas.beans.Task;
import com.jb.taas.dto.TaskDto;
import com.jb.taas.exceptions.ErrMsg;
import com.jb.taas.exceptions.TaskSystemException;
import com.jb.taas.mapper.TaskMapper;
import com.jb.taas.repos.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by kobis on 12 May, 2022
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public void addTask(TaskDto taskDto) throws TaskSystemException {
        int id = taskDto.getId();
        if(taskRepository.existsById(id)){
            throw new TaskSystemException(ErrMsg.ID_ALREADY_EXIST);
        }
        Task task = taskMapper.toDao(taskDto);
        taskRepository.save(task);
    }

    @Override
    public void updateTask(int taskId, TaskDto taskDto) throws TaskSystemException {
        taskDto.setId(taskId);

        if(!taskRepository.existsById(taskId)){
            throw new TaskSystemException(ErrMsg.ID_NOT_EXIST);
        }
        Task task = taskMapper.toDao(taskDto);
        taskRepository.saveAndFlush(task);
    }

    @Override
    public void deleteTask(int taskId) throws TaskSystemException {
        if(!taskRepository.existsById(taskId)){
            throw new TaskSystemException(ErrMsg.ID_NOT_EXIST);
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskMapper.toDtoList(taskRepository.findAll());
    }

    @Override
    public TaskDto getOneTask(int taskId) throws TaskSystemException {
        return taskMapper.toDto(taskRepository.findById(taskId).orElseThrow(()->new TaskSystemException(ErrMsg.ID_NOT_EXIST)));
    }

    @Override
    public int count() {
        return (int) taskRepository.count();
    }

    @Override
    public List<TaskDto> getAllTasksOrderByTimeAsc() {
        return taskMapper.toDtoList(taskRepository.findByOrderByWhenAsc());
    }

    @Override
    public List<TaskDto> getAllTasksOrderByTimeDesc() {
        return taskMapper.toDtoList(taskRepository.findByOrderByWhenDesc());
    }

    @Override
    public List<TaskDto> getAllTasksBetween(Timestamp startDate, Timestamp endDate) throws TaskSystemException {
        if(endDate.before(startDate)){
            throw new TaskSystemException(ErrMsg.INVALID_DATES);
        }
        return taskMapper.toDtoList(taskRepository.findByWhenBetween(startDate, endDate));
    }
}
