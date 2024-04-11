package com.zenharmonix.zenharmonixapi.Services;

import com.zenharmonix.zenharmonixapi.Models.User;
import com.zenharmonix.zenharmonixapi.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;
import com.zenharmonix.zenharmonixapi.Models.YoutubeVideo;
import com.zenharmonix.zenharmonixapi.Repositories.YoutubeRepository;
import java.util.Optional;



import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final YoutubeRepository youtubeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, YoutubeRepository youtubeRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.youtubeRepository = youtubeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User saveUser(User user) {
        // Hash the password before saving
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean checkUserCredentials(String email, String rawPassword) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isPresent()) {
            return bCryptPasswordEncoder.matches(rawPassword, foundUser.get().getPassword());
        }
        return false;
    }

    public Optional<User> authenticateUser(String email, String password) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isPresent() && bCryptPasswordEncoder.matches(password, foundUser.get().getPassword())) {
            return foundUser;
        }
        return Optional.empty();
    }

    public boolean toggleLikeSong(Long userId, Long songId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<YoutubeVideo> song = youtubeRepository.findById(songId);

        if (user.isPresent() && song.isPresent()) {
            User currentUser = user.get();
            YoutubeVideo currentSong = song.get();
            if (currentUser.getLikedSongs().contains(currentSong)) {
                currentUser.getLikedSongs().remove(currentSong);
            } else {
                currentUser.getLikedSongs().add(currentSong);
            }
            userRepository.save(currentUser); // Save the updated user
            return true;
        }
        return false;
    }

}
