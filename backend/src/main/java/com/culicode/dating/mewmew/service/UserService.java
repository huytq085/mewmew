package com.culicode.dating.mewmew.service;

import com.culicode.dating.mewmew.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User save(User user);

    User update(User user);

    List<User> getAllUser();

    User findById(int id);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User checkLogin(String username, String password);

}
