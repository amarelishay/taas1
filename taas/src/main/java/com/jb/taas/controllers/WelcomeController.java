package com.jb.taas.controllers;

import com.jb.taas.beans.ClientType;
import com.jb.taas.dto.LoginReqDto;
import com.jb.taas.dto.LoginResDto;
import com.jb.taas.dto.RegisterReqDto;
import com.jb.taas.exceptions.TaskSecurityException;
import com.jb.taas.services.AdminService;
import com.jb.taas.services.UserService;
import com.jb.taas.services.WelcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Created by kobis on 19 May, 2022
 */
@RestController
@RequestMapping("/api/welcome/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WelcomeController {
    private final AdminService adminService;
    private final UserService userService;
    private final WelcomeService welcomeService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterReqDto registerReqDto) throws TaskSecurityException {
        String email = registerReqDto.getEmail();
        String password = registerReqDto.getPassword();
        welcomeService.register(email, password);

    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) throws TaskSecurityException {
        ClientType clientType = null;
        String email = loginReqDto.getEmail();
        String password = loginReqDto.getPassword();
        UUID token = welcomeService.login(email, password);
        if (adminService.login(email, password)) {
            clientType = ClientType.ADMIN;
        }
        if (userService.login(email, password)) {
            clientType = ClientType.USER;
        }

        return new LoginResDto(clientType, email, token);
    }


}
