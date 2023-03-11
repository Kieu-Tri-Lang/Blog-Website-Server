package com.kieutrilang.blogwebsite.topic;

import com.kieutrilang.blogwebsite.post.Post;
import com.kieutrilang.blogwebsite.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "bw_topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    private List<Post> posts;

    @ManyToMany
    private List<User> followers;

}
