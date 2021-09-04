package com.weitheshinobi.forum.entity;

import com.weitheshinobi.forum.entity.Article;
import com.weitheshinobi.forum.entity.Board;
import com.weitheshinobi.forum.entity.Comment;
import com.weitheshinobi.forum.entity.Role;
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
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastOnlineDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Collection<Article> yourArticles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private Collection<Comment> yourComments = new ArrayList<>();

    @ManyToMany
    private Collection<Article> followingArticles = new HashSet<>();

    @ManyToMany
    private Collection<Article> likeArticle = new HashSet<>();

    @ManyToMany
    private Collection<Board> followingBoard = new HashSet<>();



}
