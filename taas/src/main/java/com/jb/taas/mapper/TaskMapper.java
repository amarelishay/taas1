package com.jb.taas.mapper;

import com.jb.taas.beans.Task;
import com.jb.taas.dto.TaskDto;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kobis on 12 May, 2022
 */
@Component
public class TaskMapper implements Mapper<Task, TaskDto> {
    @Override
    public Task toDao(TaskDto taskDto) {
        Date date=new Date(taskDto.getDueDate().getTime());
        return Task.builder()
                .description(taskDto.getInfo())
                .group(taskDto.getClassification())
                .title(taskDto.getCaption())
                .id(taskDto.getId())
                .when(date)
                .build();
    }

    @Override
    public TaskDto toDto(Task task) {
        return TaskDto.builder()
                .caption(task.getTitle())
                .id(task.getId())
                .dueDate(task.getWhen())
                .classification(task.getGroup())
                .info(task.getDescription())
                .build();
    }

    @Override
    public List<Task> toDaoList(List<TaskDto> taskDtos) {
        return taskDtos.stream().map(this::toDao).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> toDtoList(List<Task> tasks) {
        List<TaskDto> taskDtos=new ArrayList<>();
        for (Task task:tasks) {
            taskDtos.add(toDto(task));
        }
        return taskDtos;
    }
}
