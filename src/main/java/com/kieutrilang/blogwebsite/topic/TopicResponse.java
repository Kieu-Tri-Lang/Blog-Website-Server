package com.kieutrilang.blogwebsite.topic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TopicResponse implements Serializable {

    private String name;

    private Integer numberOfPost;

    private Integer numberOfFollower;
}
