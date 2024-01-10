package com.springbootjwt.security.user;

import com.springbootjwt.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final JwtService jwtService;
    private final UserRepository repository;
    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ){
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = repository.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .role(user.getRole().toString())
                        .imgUrl(user.getImgUrl())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(userResponses);
    }

    @PatchMapping("/info")
    public ResponseEntity<?> changeUserInfo(
            @RequestBody ChangeUserInfoRequest request,
            Principal connectedUser
    ){
        service.changeUserInfo(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo(@RequestHeader("Authorization") String jwt){
        final String userEmail;
        String token = jwt.substring(7);
        userEmail = jwtService.extractUsername(token);
        var user = repository.findByEmail(userEmail)
                .orElseThrow();
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().toString())
                .imgUrl(user.getImgUrl())
                .build();
        return ResponseEntity.ok(userResponse);
    }

}
