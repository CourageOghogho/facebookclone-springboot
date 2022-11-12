package dev.decagon.facebookcloneapp.repositories;

import dev.decagon.facebookcloneapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUserId(Integer userId);


}
