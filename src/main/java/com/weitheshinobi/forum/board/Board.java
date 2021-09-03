package com.weitheshinobi.forum.board;

import com.weitheshinobi.forum.article.Article;
import com.weitheshinobi.forum.user.User;
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

    protected Board() {
    }

    public Board(String boradName, boolean isOpen) {
        this.boradName = boradName;
        this.isOpen = isOpen;
    }

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String boradName;
    @Column(nullable = false)
    private boolean isOpen;

    @OneToMany(mappedBy = "board")
    private List<Article> articles = new ArrayList<>();

    @ManyToMany(mappedBy = "followingBoard")
    private Set<User> followers = new HashSet<>();


}
