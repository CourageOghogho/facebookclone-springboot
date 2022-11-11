package dev.decagon.facebookcloneapp.repositories;

import dev.decagon.facebookcloneapp.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    Login findByEmail(String email);
}
