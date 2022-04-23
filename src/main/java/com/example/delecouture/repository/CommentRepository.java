package com.example.delecouture.repository;

import com.example.delecouture.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPost_PostId(long pid);
}
