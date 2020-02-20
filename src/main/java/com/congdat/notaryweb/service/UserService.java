package com.congdat.notaryweb.service;

import com.congdat.notaryweb.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(String id);

    User findByUsername(String username);

    User save(User user) throws IOException;

    void updateAvatar(MultipartFile avatar, String id) throws IOException;

    User updateInfoAndChangePassword(User user, String newPassword, String id);

    User updateInfoAndDontChangePassword(User user, String id);

}
