package com.zenharmonix.zenharmonixapi.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.zenharmonix.zenharmonixapi.Models.YoutubeVideo;
import com.zenharmonix.zenharmonixapi.Response.YoutubeSearchResponse;
import java.util.List;
import java.util.ArrayList;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random; // Correct import for Random
import java.util.Arrays; // Correct import for Arrays

@Service
public class YoutubeService {
    @Value("${YOUTUBE_API_KEY}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private final Random random = new Random();
    private final List<String> orderOptions = Arrays.asList("date", "rating", "relevance", "title", "videoCount", "viewCount");

    public List<YoutubeVideo> fetchPlaylists(List<String> tags) {
        String query = String.join(" ", tags) ;
        String order = orderOptions.get(random.nextInt(orderOptions.size()));
        String searchUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" +
                URLEncoder.encode(query, StandardCharsets.UTF_8) +
                "&order=" + order +
                "&type=video" +
                "&videoCategoryId=10" + // Specify music category
                "&maxResults=10" +
                "&key=" + apiKey;

        YoutubeSearchResponse response = restTemplate.getForObject(searchUrl, YoutubeSearchResponse.class);
        return convertToPlaylist(response);
    }

    private List<YoutubeVideo> convertToPlaylist(YoutubeSearchResponse response) {
        List<YoutubeVideo> videos = new ArrayList<>();
        if (response != null && response.getItems() != null) {
            for (YoutubeSearchResponse.Item item : response.getItems()) {
                YoutubeVideo video = new YoutubeVideo();
                video.setTitle(item.getSnippet().getTitle());
                video.setYoutubeVideoId(item.getId().getVideoId());
                if (item.getSnippet().getThumbnails() != null && item.getSnippet().getThumbnails().getMedium() != null) {
                    video.setThumbnailUrl(item.getSnippet().getThumbnails().getMedium().getUrl());
                }

                videos.add(video);
            }
        }
        return videos;
    }
}