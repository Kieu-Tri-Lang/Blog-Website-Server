package com.kieutrilang.blogwebsite.post;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.kieutrilang.blogwebsite.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private User author;

    private int views;

    private int upvotes;

    private int downvotes;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}
