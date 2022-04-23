package com.example.delecouture.service.serviceImpl;

import com.example.delecouture.entity.Comment;
import com.example.delecouture.entity.Likes;
import com.example.delecouture.entity.Post;
import com.example.delecouture.entity.User;
import com.example.delecouture.repository.CommentRepository;
import com.example.delecouture.repository.LikesRepository;
import com.example.delecouture.repository.PostRepository;
import com.example.delecouture.repository.UserRepository;
import com.example.delecouture.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class LikeServiceImpl implements LikeService {


    private final LikesRepository likesRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    @Autowired
    public LikeServiceImpl(LikesRepository likesRepository, UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.likesRepository = likesRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Long likePost(Long postId, Long userId) {

        Post post = postRepository.getById(postId);
        User user = userRepository.getById(userId);

        Likes likes = new Likes();
        likes.setPost(post);
        likes.setUser(user);
        likesRepository.save(likes);
        post.setNoOfLikes((int) getAllPostLikes(postId));
        postRepository.save(post);
        return getAllPostLikes(postId);

    }

    @Override
    public Long unlikePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(userId).get();
        Likes likes = likesRepository.findByPostAndUser(post, user);

        if (likes != null) {
           likesRepository.delete(likes);
            Post postUpdate = postRepository.getById(postId);
            post.setNoOfLikes((int) getAllPostLikes(postId));
            postRepository.save(postUpdate);
        }
        return getAllPostLikes(postId);

    }

    @Override
    public Long likeComment(Long commentId, Long userId) {

        Comment comment = commentRepository.getById(commentId);
        User user = userRepository.getById(userId);
        Likes likes = new Likes();
        likes.setComment(comment);
        likes.setUser(user);
        likesRepository.save(likes);
        comment.setLikes((int) getAllPostLikes(commentId));
        commentRepository.save(comment);
        return getAllPostLikes(commentId);


    }

    @Override
    public long unlikeComment(Long userId, Long commentId) {

       Likes likes = likesRepository.findByUser_IdAndComment_Id(userId,commentId);
       if(likes != null) {
           likesRepository.delete(likes);
           Comment comment = commentRepository.findById(commentId).get();
           comment.setLikes(((int) getAllPostLikes(commentId)));
           commentRepository.save(comment);
       }
       return getAllCommentLikes(commentId);
    }

    @Override
    public long getAllCommentLikes(Long commentId) {
        return likesRepository.findAllByComment_Id(commentId).size();
    }

    @Override
    public long getAllPostLikes(Long postId) {
        return likesRepository.findAllByPost_PostId(postId).size();
    }


}
