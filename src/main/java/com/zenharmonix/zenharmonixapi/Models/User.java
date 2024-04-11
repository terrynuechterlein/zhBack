package com.zenharmonix.zenharmonixapi.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import java.util.List;

@Entity
@Table(name = "app_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_liked_songs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<YoutubeVideo> likedSongs; // List of liked songs

    // Constructors, Getters, and Setters

    public User() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<YoutubeVideo> getLikedSongs() {
        return likedSongs;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLikedSongs(List<YoutubeVideo> likedSongs) {
        this.likedSongs = likedSongs;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", likedPlaylists=" + likedSongs +
                '}';
    }

    public void likePlaylist(YoutubeVideo video) {
        this.likedSongs.add(video);
    }

    public void unlikePlaylist(YoutubeVideo video) {
        this.likedSongs.remove(video);
    }
}
