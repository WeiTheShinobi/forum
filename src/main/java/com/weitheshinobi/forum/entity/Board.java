package com.weitheshinobi.forum.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table
public class Board {

    // ID 討論版名 討論版文章 版主

    protected Board() {
    }

    public Board(String boardName, boolean isOpen) {
        this.boardName = boardName;
        this.isOpen = isOpen;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String boardName;
    private boolean isOpen;

//    @OneToMany(mappedBy = "board")
//    private List<Article> articles = new ArrayList<>();

//    @ManyToMany(mappedBy = "followingBoard")
//    private Set<User> followers = new HashSet<>();

}
