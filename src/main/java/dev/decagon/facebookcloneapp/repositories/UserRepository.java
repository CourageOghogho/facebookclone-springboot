package dev.decagon.facebookcloneapp.repositories;


import dev.decagon.facebookcloneapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);

}
