package com.kieutrilang.blogwebsite.post;

import com.kieutrilang.blogwebsite.file.File;
import com.kieutrilang.blogwebsite.topic.Topic;
import com.kieutrilang.blogwebsite.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "bw_post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @OneToOne
    private File thumbnailPicture;

    private String title;

    private String content;

    @ManyToOne
    private User author;

    @ManyToOne
    private Topic topic;

    @Enumerated(EnumType.STRING)
    private PostState postState;

    private Integer views;

    private Integer upVotes;

    private Integer downVotes;

    private Boolean isDeleted;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}
