package dev.decagon.facebookcloneapp.services;

import dev.decagon.facebookcloneapp.dto.LoginDTO;
import dev.decagon.facebookcloneapp.model.Login;
import dev.decagon.facebookcloneapp.model.User;

public interface LoginService {
    User login(String email, String password);
    Login save(LoginDTO login);
    Boolean editPassword(String email,String oldPassword, String newPassword);
}
