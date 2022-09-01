package com.jb.taas.controllers;

import com.jb.taas.beans.Task;
import com.jb.taas.dto.TaskDto;
import com.jb.taas.dto.UserDto;
import com.jb.taas.exceptions.SecMsg;
import com.jb.taas.exceptions.TaskSecurityException;
import com.jb.taas.security.TokenManager;
import com.jb.taas.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by kobis on 19 May, 2022
 */
@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
    private final TokenManager tokenManager;
    private final AdminService adminService;


    @GetMapping("tasks/count")
    public int countTasks(@RequestHeader("Authorization") UUID token) throws TaskSecurityException {
       if (!tokenManager.isTokenValid(token)){
           throw new TaskSecurityException(SecMsg.INVALID_TOKEN);

       }
        return adminService.countTasks();
    }

    @GetMapping("users/count")
    public int countUsers(@RequestHeader("Authorization") UUID token) throws TaskSecurityException {
        if (!tokenManager.isTokenValid(token)) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);

        }
        return adminService.countUsers();
    }


    @GetMapping("tasks")
    public List<TaskDto> getAllTasks(@RequestHeader("Authorization") UUID token) throws TaskSecurityException {
        if (!tokenManager.isTokenValid(token)) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return adminService.getAllTasks();

    }

    @GetMapping("users")
    public List<UserDto> getAllUsers(@RequestHeader("Authorization") UUID token) throws TaskSecurityException {
        if (!tokenManager.isTokenValid(token)) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return adminService.getAllUsers();
    }
}
