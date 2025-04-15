package com.subham.lld.musicplayeranalytics;


import com.subham.lld.musicplayeranalytics.service.AnalyticsService;
import com.subham.lld.musicplayeranalytics.service.SongService;

/**
 * Author: the_odd_human
 * Date: 15/04/25
 */
public class Test {

    public static void main(String[] args) {
        SongService songService = new SongService();
        AnalyticsService analyticsService = new AnalyticsService();
        songService.addSong(1L, "yo");
        songService.addSong(2L, "Kal Ho na HO");
        songService.addSong(3L, "kuch kuch hota hain");
        songService.addSong(4L, "sawariya");
        songService.addSong(5L, "jamuri");
        songService.addSong(6L, "holo");

        analyticsService.playSong(1L, 1L);
        analyticsService.playSong(2L, 1L);
        analyticsService.playSong(3L, 1L);
        analyticsService.playSong(4L, 1L);
        analyticsService.playSong(1L, 1L);
        analyticsService.playSong(4L, 1L);
        analyticsService.playSong(4L, 1L);
        analyticsService.playSong(1L, 2L);
        analyticsService.playSong(2L, 2L);
        analyticsService.playSong(3L, 2L);
        analyticsService.playSong(2L, 2L);
        analyticsService.playSong(1L, 1L);
        analyticsService.playSong(4L, 1L);
        analyticsService.playSong(6L, 2L);

        System.out.println(analyticsService.topKSongs(4));

        System.out.println(analyticsService.topKSongsByUser(1L, 4));



    }
}
