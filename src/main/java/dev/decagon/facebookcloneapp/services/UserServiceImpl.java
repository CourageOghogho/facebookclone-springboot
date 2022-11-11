package dev.decagon.facebookcloneapp.services;

import dev.decagon.facebookcloneapp.dto.UserDTO;
import dev.decagon.facebookcloneapp.exeption.EntityRepositoryExeption;
import dev.decagon.facebookcloneapp.model.User;
import dev.decagon.facebookcloneapp.repositories.UserRepository;
import dev.decagon.facebookcloneapp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAllUsers(String name) {
        return userRepository.findAll();
    }

    @Override
    public UserDTO update(Integer userId, UserDTO userDTO){
       User user=userRepository.findById(userId)
               .orElseThrow(()->new EntityRepositoryExeption("User not found"));
       return Mapper.userToUserDto(user);

    }
    @Override
    public User save(UserDTO user) {
       return userRepository.save(Mapper.userDTOtoUserMapper(user));

    }

    @Override
    public void delete(Integer userId) {
         userRepository.deleteById(userId);
    }


}
