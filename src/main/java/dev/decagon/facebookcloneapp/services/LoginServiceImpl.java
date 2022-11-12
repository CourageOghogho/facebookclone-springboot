package dev.decagon.facebookcloneapp.services;

import dev.decagon.facebookcloneapp.dto.LoginDTO;
import dev.decagon.facebookcloneapp.exeption.EntityRepositoryExeption;
import dev.decagon.facebookcloneapp.exeption.LoginException;
import dev.decagon.facebookcloneapp.model.Login;
import dev.decagon.facebookcloneapp.model.User;
import dev.decagon.facebookcloneapp.repositories.LoginRepository;
import dev.decagon.facebookcloneapp.repositories.UserRepository;
import dev.decagon.facebookcloneapp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class LoginServiceImpl implements LoginService {
    private  final UserRepository userRepository;
    private  final LoginRepository loginRepository;
    @Autowired
    private  Mapper mapperService;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    public User login(String email, String password){

        Login login=loginRepository.findByEmail(email);
        if(login!=null){
            if(Objects.equals(login.getPassword(), password)){
                return userRepository.findById(login.getUserId()).get();
            }
        }
        throw new LoginException("Wrong login input");
    }

    @Override
    public Login save(LoginDTO login) {
        return loginRepository.save(mapperService.loginDTOtoLoginMapper(login));
    }

    @Override
    public Boolean editPassword(String email, String oldPassword, String newPassword)throws EntityRepositoryExeption {
        Boolean result=false;

        Login login=loginRepository.findByEmail(email);
        if (Objects.equals(login.getPassword(), oldPassword)){
            login.setPassword(newPassword);
            loginRepository.saveAndFlush(login);
            result=true;
        }
        return result;
    }
    
}
