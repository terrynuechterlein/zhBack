package com.zenharmonix.zenharmonixapi.Repositories;

import com.zenharmonix.zenharmonixapi.Models.YoutubeVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YoutubeRepository extends JpaRepository<YoutubeVideo, Long> {
}
