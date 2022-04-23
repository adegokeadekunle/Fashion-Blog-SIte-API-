package com.example.delecouture.repository;

import com.example.delecouture.entity.Likes;
import com.example.delecouture.entity.Post;
import com.example.delecouture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
   List<Likes> findAllByPost_PostId(long postId);
   List<Likes> findAllByComment_Id(long commentId);
//    Likes findByUserIdAndPostId(Long userId, Long postId);
  // Likes findByUserIdAndCommentId(Long userId, Long commentId);
   Likes findByUser_IdAndComment_Id(Long userId, Long commentId);

    Likes findByPostAndUser(Post post, User user);
}
