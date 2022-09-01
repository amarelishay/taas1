package com.jb.taas.services;

import com.jb.taas.beans.ClientType;
import com.jb.taas.beans.User;
import com.jb.taas.exceptions.SecMsg;
import com.jb.taas.exceptions.TaskSecurityException;
import com.jb.taas.repos.UserRepository;
import com.jb.taas.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by kobis on 19 May, 2022
 */
@Service
@RequiredArgsConstructor
public class WelcomeServiceImpl implements WelcomeService{
    private final AdminService adminService;
    private final UserRepository userRepository;
    private final TokenManager tokenManager;
    @Override
    public void register(String email, String password) throws TaskSecurityException {

        User user = User.builder()
                .email(email)
                .password(password)
                .type(ClientType.USER)
                .build();

        if(userRepository.existsByEmail(email)){
            throw new TaskSecurityException(SecMsg.EMAIL_ALREADY_EXIST);
        }


        userRepository.save(user);
    }

    @Override
    public UUID login(String email, String password) throws TaskSecurityException {
        if(!adminService.login(email, password)&&!userRepository.existsByEmailAndPassword(email,password)){
                throw new TaskSecurityException(SecMsg.EMAIL_OR_PASSWORD_INCORRECT);
        }
        UUID token = tokenManager.add(email,password);
        return token;
    }
}
