package com.kieutrilang.blogwebsite.comment;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentResponse implements Serializable {

    private Long id;

    private String content;

    private Integer numberOfChildComment;

    private Integer upVotes;

    private Integer downVotes;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
