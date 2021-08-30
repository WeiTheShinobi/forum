package com.weitheshinobi.forum.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
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

    @ManyToMany(mappedBy = "followingBoard")
    private Set<User> followers = new HashSet<>();

}
