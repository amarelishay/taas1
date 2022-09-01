package com.jb.taas.repos;

import com.jb.taas.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.net.ssl.SSLSession;

/**
 * Created by kobis on 19 May, 2022
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    User findTop1ByEmail(String email);


}
