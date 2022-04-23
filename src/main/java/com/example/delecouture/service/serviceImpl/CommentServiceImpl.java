package com.example.delecouture.service.serviceImpl;

import com.example.delecouture.dto.CommentDto;
import com.example.delecouture.entity.Comment;
import com.example.delecouture.entity.Post;
import com.example.delecouture.entity.User;
import com.example.delecouture.exceptions.InvalidRecordException;
import com.example.delecouture.exceptions.UnAuthorizedActionException;
import com.example.delecouture.repository.CommentRepository;
import com.example.delecouture.repository.PostRepository;
import com.example.delecouture.repository.UserRepository;
import com.example.delecouture.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository   postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Comment addComment(Long userId, Long postId, CommentDto commentDto) {
            Comment comment = new Comment();
            User user = userRepository.findById(userId).get();
            Post post = postRepository.findById(postId).get();
            LocalDateTime date = LocalDateTime.now();
            comment.setCommentContent(commentDto.getCommentContent());
            comment.setCommentedDate(date);
            comment.setUser(user);
            comment.setPost(post);

        commentRepository.save(comment);

        return comment;
    }

    @Override
    public boolean deleteComment(Long commentId, Long userId) {
       Comment comment  = commentRepository.findById(commentId).get();
       if (comment.getUser().getId() != userId) throw new InvalidRecordException("comment not found!");
        commentRepository.delete(comment);
        return false;
    }

    @Override
    public Comment updateComment(Long commentId, Long userId, CommentDto commentDto) {
         Comment comment = commentRepository.findById(commentId).get();
        if (comment.getUser().getId() == userId) {
            comment.setCommentContent(commentDto.getCommentContent());
            commentRepository.save(comment);
        }
        else{
            throw new UnAuthorizedActionException("You cannot update this comment!");
        }

        return comment;
    }

    @Override
    public Comment getComment(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            return commentOptional.get();
        }
        throw new InvalidRecordException("comment not found!");
    }

    @Override
    public List<Comment> getAllComments(Long postId) {

            return commentRepository.findCommentsByPost_PostId(postId);

    }


}
