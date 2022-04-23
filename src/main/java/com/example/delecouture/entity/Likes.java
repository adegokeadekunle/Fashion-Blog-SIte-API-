package com.example.delecouture.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likesId;
    @ManyToOne
    @JoinColumn(name = "post_post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "comment_comment_id")
    private Comment comment;
    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user;

}
