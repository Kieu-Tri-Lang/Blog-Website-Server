package com.kieutrilang.blogwebsite.comment;

import com.kieutrilang.blogwebsite.post.Post;
import com.kieutrilang.blogwebsite.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "bw_comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Long parentId;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    private Integer upVotes;

    private Integer downVotes;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean isDeleted;
}
