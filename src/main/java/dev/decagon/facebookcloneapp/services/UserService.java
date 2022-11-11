package dev.decagon.facebookcloneapp.services;

import dev.decagon.facebookcloneapp.dto.UserDTO;
import dev.decagon.facebookcloneapp.model.User;

import java.util.List;

public interface UserService {
    User get(Integer id);
    List<User> getAllUsers(String name);
    UserDTO update(Integer userId, UserDTO userDTO);

    User save(UserDTO user);

    void delete(Integer userid);


}
