package com.example.delecouture.service;

import com.example.delecouture.dto.PostDto;
import com.example.delecouture.entity.Post;

import java.util.List;

public interface PostService {
    Post createNewPost(Long userId,PostDto postDto);
    Post editPost(Long postId,Long userId,PostDto postDto);
    boolean deletePost(Long postId,Long userId);
    List<Post> getAllPost();
    Post getPost(Long id);

}
