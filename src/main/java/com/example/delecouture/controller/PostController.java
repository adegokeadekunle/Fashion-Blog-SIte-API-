package com.example.delecouture.controller;


import com.example.delecouture.dto.CommentDto;
import com.example.delecouture.dto.PostDto;
import com.example.delecouture.entity.Comment;
import com.example.delecouture.entity.Likes;
import com.example.delecouture.entity.Post;
import com.example.delecouture.repository.LikesRepository;
import com.example.delecouture.service.CommentService;
import com.example.delecouture.service.LikeService;
import com.example.delecouture.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/posts")
@RestController
public class PostController {


    private final PostService postService;
    private final CommentService commentService;
    private final  LikesRepository likesRepository;
    private final LikeService likeService;

    @Autowired
    public PostController(PostService postService, CommentService commentService, LikesRepository likesRepository, LikeService likeService) {
        this.postService = postService;
        this.commentService = commentService;
        this.likesRepository = likesRepository;
        this.likeService = likeService;
    }

    @PostMapping("/{userId}/newPost")
    public ResponseEntity<Post> createNewPost(@PathVariable(value = "userId") Long userId, @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.createNewPost(userId,postDto));
    }
    @PutMapping("/update/{postId}/{userId}")
    public ResponseEntity<Post> updatePost(@PathVariable("postId") Long postId,@PathVariable("userId") Long userId, @RequestBody PostDto postDto) {
        return ResponseEntity.ok( postService.editPost(postId,userId,postDto));
    }
    @DeleteMapping("/deletepost/{postId}/{userId}")
    public String deletePost(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {
        return postService.deletePost(postId,userId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {

        return ResponseEntity.ok(postService.getPost(id));
    }
    @GetMapping("/allPost")
    public ResponseEntity<Iterable<Post>> getAllPosts() {

        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping("/likes/{id}")
    public ResponseEntity<Long> getLikesById(@PathVariable Long id) {
        return ResponseEntity.ok(likesRepository.count());
    }

    @PostMapping("/likepost/{commentId}/{userId}")
    public ResponseEntity<Long> likePost(@PathVariable("commentId") Long commentId,@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(likeService.likePost(commentId, userId));
    }

    @DeleteMapping("/unlikepost/{userId}/{commentId}")
    public ResponseEntity<Long> unlikePost(@PathVariable("commentId") Long commentId,@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(likeService.unlikePost(commentId, userId));
    }

    @PostMapping("/comment/{userId}/{postId}")
    public ResponseEntity<Comment> commentPost(@PathVariable(value="userId") Long userId,@PathVariable(value="postId") Long postId, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.addComment(userId, postId, commentDto));
    }

    @PutMapping("/updatecomment/{commentId}/{userId}")
    public Comment updateComment( @PathVariable("commentId") Long commentId,@PathVariable("userId") Long userId, @RequestBody CommentDto commentDto){
        return commentService.updateComment(commentId,userId,commentDto);

    }
    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @GetMapping("/all/{pid}")
    public List<Comment> getAllPostComments(@PathVariable Long postId) {
        return commentService.getAllComments(postId);
    }

    @DeleteMapping("/uncomment/{commentId}/{userId}")
    public boolean uncommentPost(@PathVariable("commentId") Long commentId,@PathVariable("userId") Long userId) {
        return commentService.deleteComment(commentId,userId);
    }


}
