package com.subham.lld.musicplayeranalytics.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author: the_odd_human
 * Date: 15/04/25
 */
@Getter
@Setter
@ToString
public class Song {
    private long songId;

    private String title;

    private long playCount;

    public Song(long songId, String title) {
        this.songId = songId;
        this.title = title;
        this.playCount = 0;
    }

    public void incrementPlayCount(int count) {
        this.playCount += count;
    }
}
