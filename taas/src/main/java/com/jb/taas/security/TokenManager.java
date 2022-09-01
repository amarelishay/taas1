package com.jb.taas.security;

import com.jb.taas.beans.ClientType;
import com.jb.taas.beans.User;
import com.jb.taas.exceptions.SecMsg;
import com.jb.taas.exceptions.TaskSecurityException;
import com.jb.taas.repos.UserRepository;
import com.jb.taas.services.AdminService;
import com.jb.taas.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Created by kobis on 19 May, 2022
 */
@Service
@RequiredArgsConstructor
public class TokenManager {
private final AdminService adminService;
private final UserService userService;
    private final Map<UUID, Information> map;
    private final UserRepository userRepository;


    public UUID add(String email, String password)  {
        Information information = new Information();
        information.setEmail(email);
        information.setTime(LocalDateTime.now());

        ClientType clientType = null;
        if (adminService.login(email, password)) {
            clientType = ClientType.ADMIN;

        }
        if (userService.login(email, password)) {
            clientType = ClientType.USER;
        }
        information.setClientType(clientType);
        switch (clientType) {
            case USER:
                information.setUserId(userRepository.findTop1ByEmail(email).getId());
                break;
            default:
                information.setUserId(1);
        }

        UUID token = UUID.randomUUID();
        map.put(token, information);
        return token;
    }

    public int getUserId(UUID token) throws TaskSecurityException {

        Information information = map.get(token);
        if (information == null) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return information.getUserId();
    }


    @Scheduled(fixedRate = 1000 * 60)
    public void deleteExpiredTokenOver30Minutes() {
        map.entrySet().removeIf(ins -> ins.getValue().getTime().isAfter(LocalDateTime.now().plusMinutes(30)));
    }
    public boolean isTokenValid(UUID token) throws SecurityException {
        if (map.get(token)!=null){
            return true;
        }
        throw new SecurityException(SecMsg.INVALID_TOKEN.getMsg());
    }
    public void removePreviousInstances(int userID) {
        map.entrySet().removeIf(ins -> ins.getValue().getUserId() == userID);
    }

}