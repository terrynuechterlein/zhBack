package com.zenharmonix.zenharmonixapi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.zenharmonix.zenharmonixapi.Services.YoutubeService;
import com.zenharmonix.zenharmonixapi.Models.YoutubeVideo;

import java.util.List;

@RestController
@RequestMapping("/api/v1/playlists")
@CrossOrigin
public class PlaylistController {

    private final YoutubeService youtubeService;

    @Autowired
    public PlaylistController(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    @GetMapping
    public ResponseEntity<?> fetchPlaylists(@RequestParam List<String> tags) {
        List<YoutubeVideo> playlists = youtubeService.fetchPlaylists(tags);
        return ResponseEntity.ok(playlists);
    }

}
