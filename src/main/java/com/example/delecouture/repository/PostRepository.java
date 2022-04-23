package com.example.delecouture.repository;

import com.example.delecouture.entity.Post;
import com.example.delecouture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
   List<Post> findPostByUser(User user);

}
