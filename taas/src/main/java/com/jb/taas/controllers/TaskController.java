package com.jb.taas.controllers;

import com.jb.taas.beans.Task;
import com.jb.taas.dto.TaskDto;
import com.jb.taas.dto.TaskPayloadDto;
import com.jb.taas.exceptions.SecMsg;
import com.jb.taas.exceptions.TaskSecurityException;
import com.jb.taas.exceptions.TaskSystemException;
import com.jb.taas.models.DatesBetweenReq;
import com.jb.taas.security.TokenManager;
import com.jb.taas.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Created by kobis on 12 May, 2022
 */
@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {
private final TokenManager tokenManager;

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(@RequestHeader("Authorization") UUID token, @Valid @RequestBody TaskPayloadDto taskDto) throws TaskSystemException {

        taskService.addTask(new TaskDto(taskDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@RequestHeader("Authorization") UUID token,@PathVariable int id,@RequestBody TaskPayloadDto taskDto) throws TaskSystemException, TaskSecurityException {
        if (!tokenManager.isTokenValid(token)) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        taskService.updateTask(id,new TaskDto(taskDto));
    }

    @GetMapping
    public List<TaskDto> getAllTasks(@RequestHeader("Authorization") UUID token){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDto getOneTask(@RequestHeader("Authorization") UUID token,@PathVariable int id) throws TaskSystemException {
        return taskService.getOneTask(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@RequestHeader("Authorization") UUID token,@PathVariable int id) throws TaskSystemException {
        taskService.deleteTask(id);
    }

    @GetMapping("count")
    public int count(){
        return taskService.count();
    }

    @GetMapping("sorted/time/asc")
    public List<TaskDto> getAllAsc(@RequestHeader("Authorization") UUID token){
        return taskService.getAllTasksOrderByTimeAsc();
    }

    @GetMapping("sorted/time/desc")
    public List<TaskDto> getAllDesc(@RequestHeader("Authorization") UUID token){
        return taskService.getAllTasksOrderByTimeDesc();
    }

    @GetMapping("btw/dates")
    public List<TaskDto> getAllTaskBetween(@RequestHeader("Authorization") UUID token,@RequestBody DatesBetweenReq datesBetween) throws TaskSystemException {
        return taskService.getAllTasksBetween(datesBetween.getStart(), datesBetween.getEnd());
    }


}
