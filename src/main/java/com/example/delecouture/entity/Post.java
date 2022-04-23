package com.example.delecouture.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String postName;
    @NotBlank
    private String content;
    private double price;
    private String category;
    private LocalDateTime postDateTime;
    @ManyToOne
    @JoinColumn(name = "user_user_id")
    @JsonIgnore
    private User user;
   // @OneToMany
    private int noOfLikes;
    @OneToMany
    List<Comment> listOfComment;
//    @OneToMany
    private int noOfComments;

}
