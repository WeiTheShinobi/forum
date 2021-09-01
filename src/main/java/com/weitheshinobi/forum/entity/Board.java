package com.weitheshinobi.forum.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "forum_board")
public class Board {

    // ID 討論版名 討論版文章 版主

    @Id
    @GeneratedValue
    private Long id;
    private String boradName;

    @OneToMany(mappedBy = "board")
    private List<Article> articles = new ArrayList<>();

    @ManyToMany(mappedBy = "followingBoard")
    private Set<User> followers = new HashSet<>();

}
