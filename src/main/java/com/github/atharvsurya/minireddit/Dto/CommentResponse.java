package com.github.atharvsurya.minireddit.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentResponse {
    private Long comment_id;
    private String content;
    private long likes;
    private LocalDateTime time;

    private String authorUsername;
    private Long post_id;
}
