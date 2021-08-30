package com.weitheshinobi.forum.entity;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.*;


@Data
@Entity
@Table(name = "forum_user")
public class User {

    // 用戶 文章 討論版 文章 追蹤的文章 追蹤討論版 留言 版主

    // ID email 密碼 權限 最後上線時間 追蹤的文章 追蹤的討論版 發表的文章 發表的留言
    // ID 文章標題 文章作者 文章內容 文章標籤 討論版 創建時間 最後編輯時間 文章留言 推 噓
    // ID 討論版名 討論版文章 版主

    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String password;
    @LastModifiedDate
    private Date lastOnlineTime;

    @OneToMany(mappedBy = "user")
    private List<Article> yourArticles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> yourComments = new ArrayList<>();

    @ManyToMany
    private Set<Article> followingArticles = new HashSet<>();

    @ManyToMany
    private Set<Article> likeArticle = new HashSet<>();

    @ManyToMany
    private Set<Board> followingBoard = new HashSet<>();



}
