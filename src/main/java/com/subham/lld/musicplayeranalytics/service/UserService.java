package com.subham.lld.musicplayeranalytics.service;


import com.subham.lld.musicplayeranalytics.repository.UserRepository;

/**
 * Author: the_odd_human
 * Date: 15/04/25
 */
public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        userRepository = UserRepository.getInstance();
    }

    public void starSong(long userId, long songId) {
        userRepository.starSong(userId, songId);
    }

    public void unstarSong(long userId, long songId) {
        userRepository.unstarSong(userId, songId);
    }
}
