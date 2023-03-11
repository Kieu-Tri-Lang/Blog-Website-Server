package com.kieutrilang.blogwebsite.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostRequest {

    private String title;

    private String content;

    private String topicName;

    private PostState postState;
}
