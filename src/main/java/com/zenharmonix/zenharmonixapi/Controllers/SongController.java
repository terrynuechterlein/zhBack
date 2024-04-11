package com.zenharmonix.zenharmonixapi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.zenharmonix.zenharmonixapi.Services.UserService;
import com.zenharmonix.zenharmonixapi.Services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import com.zenharmonix.zenharmonixapi.Principals.UserPrincipal;


@RestController
@RequestMapping(path="/api/v1/songs")
public class SongController {

    private final UserService userService;

    @Autowired
    public SongController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/like/{songId}")
    public ResponseEntity<?> likeSong(@PathVariable Long songId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getId();
        boolean success = userService.toggleLikeSong(userId, songId);
        if (success) {
            return ResponseEntity.ok().body("Song like status updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or song not found");
        }
    }
}

