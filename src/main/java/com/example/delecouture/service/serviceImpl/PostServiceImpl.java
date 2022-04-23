package com.example.delecouture.service.serviceImpl;


import com.example.delecouture.dto.PostDto;
import com.example.delecouture.entity.Post;
import com.example.delecouture.entity.User;
import com.example.delecouture.exceptions.InvalidRecordException;
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
       // post.setListOfComment(post.getListOfComment().forEach(comment -> comment.getCommentContent()));
        post.setNoOfLikes(post.getNoOfLikes());
        post.setNoOfComments(post.getNoOfComments());
        postRepository.save(post);
        return post;

    }

    @Override
    public Post editPost(Long postId,Long userId, PostDto postDto) {
        Post post  = postRepository.findById(postId).get();
        if (post.getUser().getId() == userId) {
            post.setPostName(post.getPostName());
        post.setContent(postDto.getContent());
        post.setPrice(post.getPrice());
        post.setCategory(post.getCategory());
        postRepository.save(post);
        }
        else{
            System.out.println();
        }

        return post;
    }

    @Override
    public boolean deletePost(Long postId,Long userId) {

        Post post = postRepository.findById(postId).get();
      //  if (post == null) throw new InvalidRecordException("Post not found!");
        if (post.getUser().getId() == userId)postRepository.deleteById(postId);

        return false;
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
