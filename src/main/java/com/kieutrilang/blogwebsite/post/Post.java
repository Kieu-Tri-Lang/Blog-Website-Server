package com.kieutrilang.blogwebsite.post;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

import com.kieutrilang.blogwebsite.file.File;
import com.kieutrilang.blogwebsite.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "bw_post")
public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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

    private int views;

    private int upVotes;

    private int downVotes;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}
