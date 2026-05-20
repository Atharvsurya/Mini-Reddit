package com.github.atharvsurya.minireddit.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private long likes;
    private LocalDateTime time;

    private String authorUsername;
}
