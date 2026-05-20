package com.github.atharvsurya.minireddit.Controller;

import com.github.atharvsurya.minireddit.Dto.ApiResponse;
import com.github.atharvsurya.minireddit.Dto.LoginRequest;
import com.github.atharvsurya.minireddit.Dto.RegisterRequest;
import com.github.atharvsurya.minireddit.Dto.UserResponse;
import com.github.atharvsurya.minireddit.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody RegisterRequest req){
        ApiResponse<UserResponse> res = userService.register(req);
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.badRequest().body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody LoginRequest req){
        ApiResponse<UserResponse> res = userService.login(req);
        return res.isSuccess()?ResponseEntity.ok(res):ResponseEntity.status(401).body(res);
    }
}
