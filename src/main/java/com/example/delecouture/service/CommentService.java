package com.example.delecouture.service;

import com.example.delecouture.dto.CommentDto;
import com.example.delecouture.entity.Comment;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface CommentService {
    public CommentDto addComment(Long userId, Long postId, CommentDto commentDto);
    public boolean deleteComment(Long commentId,Long userId);
    public CommentDto updateComment(Long commentId,Long userId, CommentDto commentDto);
    public  String getComment(Long commentId);
    public List<Comment> getAllComments(Long postId);
}


