package com.jb.taas.services;

import com.jb.taas.beans.Task;
import com.jb.taas.dto.TaskDto;
import com.jb.taas.exceptions.TaskSystemException;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by kobis on 12 May, 2022
 */
public interface TaskService {

    void addTask(TaskDto taskDto) throws TaskSystemException;
    void updateTask(int taskId,TaskDto taskDto) throws TaskSystemException;
    void deleteTask(int taskId) throws TaskSystemException;

    List<TaskDto> getAllTasks();
    TaskDto getOneTask(int taskId) throws TaskSystemException;

    int count();

    List<TaskDto> getAllTasksOrderByTimeAsc();
    List<TaskDto> getAllTasksOrderByTimeDesc();
    List<TaskDto> getAllTasksBetween(Timestamp startDate, Timestamp endDate) throws TaskSystemException;
}
