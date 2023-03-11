package com.kieutrilang.blogwebsite.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostResponse {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class Author{
        private String name;

        private String profilePictureLink;
    }

    private String code;

    private String title;

    private String content;

    private String thumbnailPictureLink;

    private String topicName;

    private Author author;

    private Integer views;

    private Integer upVotes;

    private Integer downVotes;

    private ZonedDateTime postedTime;

    public void setAuthor(String authorName,String profilePictureLink){
        author =  new Author(authorName,profilePictureLink);
    }

}
