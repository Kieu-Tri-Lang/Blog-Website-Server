package com.kieutrilang.blogwebsite.user;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserResponse implements Serializable {

    private String username;

    private String email;

    private int numberOfFollowers;

    private int numberOfFollowing;

    private int numberOfPosts;

    private String profilePictureLink;

    private String coverPictureLink;

}
