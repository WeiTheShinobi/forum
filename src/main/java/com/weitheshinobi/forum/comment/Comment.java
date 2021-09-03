package com.weitheshinobi.forum.comment;

import com.weitheshinobi.forum.article.Article;
import com.weitheshinobi.forum.user.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "forum_comment")
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String comment;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date modifiedDate;

    @ManyToOne
    private User user;
    @ManyToOne
    private Article article;


}
