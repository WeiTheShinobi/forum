package com.weitheshinobi.forum.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "forum_comment")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    private User user;
    @ManyToOne
    private Article article;


}
