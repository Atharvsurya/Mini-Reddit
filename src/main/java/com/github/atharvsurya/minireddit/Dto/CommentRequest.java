package com.github.atharvsurya.minireddit.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentRequest {
    private String content;
    private String authorUsername;
    private Long id;
}
