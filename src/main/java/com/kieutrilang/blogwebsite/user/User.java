package com.kieutrilang.blogwebsite.user;

import com.kieutrilang.blogwebsite.comment.Comment;
import com.kieutrilang.blogwebsite.post.Post;
import com.kieutrilang.blogwebsite.file.File;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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
@Table(name = "bw_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean gender;

    @ManyToMany
    @JoinTable(
            name = "bw_follower",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private List<User> followers;

    @ManyToMany
    @JoinTable(
            name = "bw_following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private List<User> following;

    @OneToMany
    private List<Post> posts;

    @OneToMany
    private List<Comment> comments;

    @OneToOne
    private File profilePicture;

    @OneToOne
    private File coverPicture;

}
