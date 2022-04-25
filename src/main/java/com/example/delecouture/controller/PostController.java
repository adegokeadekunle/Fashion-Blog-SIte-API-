package com.example.delecouture.controller;


import com.example.delecouture.dto.CommentDto;
import com.example.delecouture.dto.PostDto;
import com.example.delecouture.entity.Comment;
import com.example.delecouture.entity.Post;
import com.example.delecouture.repository.LikesRepository;
import com.example.delecouture.service.CommentService;
import com.example.delecouture.service.LikeService;
import com.example.delecouture.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> updatePost(@PathVariable("postId") Long postId,@PathVariable("userId") Long userId, @RequestBody PostDto postDto) {
        postService.editPost(postId,userId,postDto);
        return new ResponseEntity<>( "post edited successfully", HttpStatus.OK);
    }
    @DeleteMapping("/deletepost/{postId}/{userId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {
        postService.deletePost(postId,userId);
        return new ResponseEntity<>( "Post deleted ", HttpStatus.OK);

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

    @PostMapping("/likepost/{postId}/{userId}")
    public ResponseEntity<String> likePost(@PathVariable("postId") Long postId,@PathVariable("userId") Long userId) {
        likeService.likePost(postId, userId);
        return ResponseEntity.ok("post liked");
    }

    @DeleteMapping("/unlikepost/{postId}/{userId}")
    public ResponseEntity<String> unlikePost(@PathVariable("postId") Long postId,@PathVariable("userId") Long userId) {
        likeService.unlikePost(postId, userId);
        return ResponseEntity.ok().body("Post Unliked");
    }

    @PostMapping("/likecomment/{commentId}/{userId}")
    public ResponseEntity<Long> likeComment(@PathVariable("commentId") Long commentId,@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(likeService.likeComment(commentId, userId));
    }

    @DeleteMapping("/unlikecomment/{userId}/{commentId}")
    public ResponseEntity<Long> unlikeComment(@PathVariable("commentId") Long commentId,@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(likeService.unlikeComment(commentId, userId));
    }

    @PostMapping("/comment/{userId}/{postId}")
    public CommentDto commentPost(@PathVariable(value="userId") Long userId,@PathVariable(value="postId") Long postId, @RequestBody CommentDto commentDto) {
        commentService.addComment(userId, postId, commentDto);
        return commentDto;
    }

    @PutMapping("/updatecomment/{commentId}/{userId}")
    public CommentDto updateComment( @PathVariable("commentId") Long commentId,@PathVariable("userId") Long userId, @RequestBody CommentDto commentDto){
        return commentService.updateComment(commentId,userId,commentDto);
    }

    @GetMapping("/comments/{id}")
    public String getCommentById(@PathVariable Long id) {
        return commentService.getComment(id);
    }

    @GetMapping("/all/{pid}")
    public List<Comment> getAllPostComments(@PathVariable Long postId) {
        return commentService.getAllComments(postId);
    }

    @DeleteMapping("/uncomment/{commentId}/{userId}")
    public ResponseEntity<String> uncommentPost(@PathVariable("commentId") Long commentId,@PathVariable("userId") Long userId) {
        commentService.deleteComment(commentId,userId);
        return ResponseEntity.ok().body("Post has been uncommented");
    }

}
