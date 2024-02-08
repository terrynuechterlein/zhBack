package com.zenharmonix.zenharmonixapi.user;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;

import java.util.List;

@Entity //marks class as JPA entity
@Table(name = "app_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    //preferences
    private String currentFeeling;
    private String desiredFeeling;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_desired_manifestations")
    @Column(name = "manifestation")
    private List<String> desiredManifestations;

    //Reminder settings
    private boolean reminderEnabled;
    private String reminderTime;

    //playlist
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_saved_playlist")
    @Column(name = "playlist_item")
    private List<String> savedPlaylist;

   //constructor
    public User() {
    }

    public User(Long id,
                String username,
                String email,
                String password,
                String currentFeeling,
                String desiredFeeling,
                List<String> desiredManifestations,
                boolean reminderEnabled,
                String reminderTime,
                List<String> savedPlaylist) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.currentFeeling = currentFeeling;
        this.desiredFeeling = desiredFeeling;
        this.desiredManifestations = desiredManifestations;
        this.reminderEnabled = reminderEnabled;
        this.reminderTime = reminderTime;
        this.savedPlaylist = savedPlaylist;
    }


    public User(String username,
                String email,
                String password,
                String currentFeeling,
                String desiredFeeling,
                List<String> desiredManifestations,
                boolean reminderEnabled,
                String reminderTime,
                List<String> savedPlaylist) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.currentFeeling = currentFeeling;
        this.desiredFeeling = desiredFeeling;
        this.desiredManifestations = desiredManifestations;
        this.reminderEnabled = reminderEnabled;
        this.reminderTime = reminderTime;
        this.savedPlaylist = savedPlaylist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrentFeeling() {
        return currentFeeling;
    }

    public void setCurrentFeeling(String currentFeeling) {
        this.currentFeeling = currentFeeling;
    }

    public String getDesiredFeeling() {
        return desiredFeeling;
    }

    public void setDesiredFeeling(String desiredFeeling) {
        this.desiredFeeling = desiredFeeling;
    }

    public List<String> getDesiredManifestations() {
        return desiredManifestations;
    }

    public void setDesiredManifestations(List<String> desiredManifestations) {
        this.desiredManifestations = desiredManifestations;
    }

    public boolean isReminderEnabled() {
        return reminderEnabled;
    }

    public void setReminderEnabled(boolean reminderEnabled) {
        this.reminderEnabled = reminderEnabled;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public List<String> getSavedPlaylist() {
        return savedPlaylist;
    }

    public void setSavedPlaylist(List<String> savedPlaylist) {
        this.savedPlaylist = savedPlaylist;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", currentFeeling='" + currentFeeling + '\'' +
                ", desiredFeeling='" + desiredFeeling + '\'' +
                ", desiredManifestations=" + desiredManifestations +
                ", reminderEnabled=" + reminderEnabled +
                ", reminderTime='" + reminderTime + '\'' +
                ", savedPlaylist=" + savedPlaylist +
                '}';
    }
}

// Constructors

