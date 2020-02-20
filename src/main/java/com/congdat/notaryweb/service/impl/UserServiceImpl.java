package com.congdat.notaryweb.service.impl;

import com.congdat.notaryweb.model.User;
import com.congdat.notaryweb.repository.UserRepository;
import com.congdat.notaryweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        user.setCreatedBy(user.getUsername());
        user.setCreatedDate(new Date(System.currentTimeMillis()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public void updateAvatar(MultipartFile avatar, String id) throws IOException {
        User foundUser = userRepository.findById(id).orElse(null);
        Path resourcePath = Paths.get("src/main/resources/static/uploads/avatar");
        Path avatarPath = Paths.get(resourcePath.toAbsolutePath() + "/" + avatar.getOriginalFilename());
        Files.write(avatarPath, avatar.getBytes());
        assert foundUser != null;
        foundUser.setAvatarPath(avatarPath.toString());
        foundUser.setAvatarName(avatar.getOriginalFilename());
        userRepository.save(foundUser);
    }

    @Override
    public User updateInfoAndChangePassword(User user, String newPassword, String id) {
        User foundUser = updateInfo(user, id);
        foundUser.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(foundUser);
    }

    @Override
    public User updateInfoAndDontChangePassword(User user, String id) {
        User foundUser = updateInfo(user, id);
        return userRepository.save(foundUser);
    }

    private User updateInfo(User user, String id) {
        User foundUser = userRepository.findById(id).orElse(null);
        assert foundUser != null;
        foundUser.setFullName(user.getFullName());
        foundUser.setEmail(user.getEmail());
        foundUser.setPhone(user.getPhone());
        foundUser.setModifiedBy(user.getFullName());
        foundUser.setModifiedDate(new Date(System.currentTimeMillis()));
        return foundUser;
    }
}
