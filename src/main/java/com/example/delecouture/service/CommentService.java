package com.example.delecouture.service;

import com.example.delecouture.dto.CommentDto;
import com.example.delecouture.entity.Comment;
import com.example.delecouture.entity.User;

import java.util.List;

public interface CommentService {
    public Comment addComment(Long userId, Long postId, CommentDto commentDto);
    public boolean deleteComment(Long commentId,Long userId);
    public Comment updateComment(Long commentId,Long userId, CommentDto commentDto);
    public Comment getComment(Long commentId);
    public List<Comment> getAllComments(Long postId);
}


