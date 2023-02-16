package com.kieutrilang.blogwebsite.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentRequest {

    private String content;

    private String username;
}
