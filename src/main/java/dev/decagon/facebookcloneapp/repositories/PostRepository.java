package dev.decagon.facebookcloneapp.repositories;

import dev.decagon.facebookcloneapp.model.Post;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Registered
public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUserId(Integer userId);


}
