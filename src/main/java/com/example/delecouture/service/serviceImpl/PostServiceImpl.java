package com.example.delecouture.service.serviceImpl;


import com.example.delecouture.dto.PostDto;
import com.example.delecouture.entity.Comment;
import com.example.delecouture.entity.Post;
import com.example.delecouture.entity.User;
import com.example.delecouture.exceptions.InvalidRecordException;
import com.example.delecouture.exceptions.UnAuthorizedActionException;
import com.example.delecouture.repository.CommentRepository;
import com.example.delecouture.repository.PostRepository;
import com.example.delecouture.repository.UserRepository;
import com.example.delecouture.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Post createNewPost(Long userId,PostDto postDto) {

        User user  = userRepository.findById(userId).get();
        LocalDateTime dateTime= LocalDateTime.now();
        Post post  = new Post();
        post.setPostName(postDto.getPostName());
        post.setContent(postDto.getContent());
        post.setPrice(postDto.getPrice());
        post.setCategory(postDto.getCategory());
        post.setPostDateTime(dateTime);
        post.setUser(user);
      //  post.setNoOfLikes(post.getNoOfLikes());
       // post.setNoOfComments(post.getNoOfComments());
        postRepository.save(post);
        return post;

    }

    @Override
    public Post editPost(Long postId,Long userId, PostDto postDto) {
        Post post  = postRepository.getById(postId);
        if (post.getUser().getId() == userId) {
            post.setPostName(postDto.getPostName());
            post.setContent(postDto.getContent());
            post.setPrice(postDto.getPrice());
            post.setCategory(postDto.getCategory());
        postRepository.save(post);
        }

        return post;

    }

    @Override
    public String deletePost(Long postId,Long userId) {

        Post post = postRepository.getById(postId);

        if (post.getUser().getId() == userId){

            postRepository.deleteById(postId);

        }else{
            throw new UnAuthorizedActionException("Post not found!");
        }
        return "Delete successful.";
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post getPost(Long id) {

        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null);
    }
}
