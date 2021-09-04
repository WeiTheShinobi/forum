package com.weitheshinobi.forum.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;


@Data
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Article {

    // ID 文章標題 文章作者 文章內容 文章標籤 討論版 創建時間 最後編輯時間 文章留言 推 噓



    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private boolean isOpen;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date modifiedDate;


    @ManyToOne
    private User user;

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "followingArticles")
    private Set<User> followers = new HashSet<>();

    @ManyToMany(mappedBy = "likeArticle")
    private Set<User> likeUsers = new HashSet<>();



}
