package com.github.atharvsurya.minireddit.Controller;

import com.github.atharvsurya.minireddit.Dto.ApiResponse;
import com.github.atharvsurya.minireddit.Dto.CommentRequest;
import com.github.atharvsurya.minireddit.Dto.CommentResponse;
import com.github.atharvsurya.minireddit.Service.CommentService;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CommentResponse>> createComment(@RequestBody CommentRequest req){
        ApiResponse<CommentResponse> res = commentService.createcomment(req);
        return res.isSuccess()? ResponseEntity.ok(res): ResponseEntity.badRequest().body(res);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<ApiResponse<List<CommentResponse>>> showAllComments(@PathVariable Long id){
        ApiResponse<List<CommentResponse>> res = commentService.showAlComments(id);
        return res.isSuccess()? ResponseEntity.ok(res): ResponseEntity.badRequest().body(res);
    }
}
