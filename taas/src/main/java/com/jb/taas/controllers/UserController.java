package com.jb.taas.controllers;

import com.jb.taas.dto.TaskDto;
import com.jb.taas.exceptions.TaskSecurityException;
import com.jb.taas.exceptions.TaskSystemException;
import com.jb.taas.security.TokenManager;
import com.jb.taas.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;
    private final TokenManager tokenManager;

    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks(@RequestHeader("Authorization")UUID token) throws TaskSecurityException {

        int userId = tokenManager.getUserId(token);
        return userService.getAllTasks(userId);
    }

    @PostMapping("/tasks")
    public void addTask(@RequestHeader("Authorization")UUID token,@RequestBody TaskDto taskDto) throws TaskSystemException, TaskSecurityException {
        int userId = tokenManager.getUserId(token);
        userService.addTask(userId, taskDto);
    }

}
