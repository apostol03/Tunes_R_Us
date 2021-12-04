package com.yankovltd.tunes.service;

public interface StatsService {

    void userAlbumVisits();

    int getAlbumVisits();

    void userArtistVisits();

    int getArtistVisits();

    void clearVisits();

    String onRequestPeekPanel();


    String onRequestDeleteUser();
}
