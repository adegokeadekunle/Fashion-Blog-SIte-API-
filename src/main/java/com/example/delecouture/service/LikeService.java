package com.example.delecouture.service;

import com.example.delecouture.entity.Likes;

public interface LikeService {
    public Long likePost(Long postId, Long userId);
    public Long unlikePost(Long postId, Long userId);
    public Likes likeComment(Long commentId, Long userId);
    public long unlikeComment(Long userId, Long commentId);
    long getAllCommentLikes(Long commentId);
    long getAllPostLikes(Long postId);
}
