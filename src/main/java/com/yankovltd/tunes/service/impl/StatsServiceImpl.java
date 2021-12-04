package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.service.StatsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

    private int albumCount, artistCount;

    @Override
    public void userAlbumVisits() {
        albumCount++;
    }

    @Override
    public int getAlbumVisits() {
        return albumCount;
    }

    @Override
    public void userArtistVisits() {
        artistCount++;
    }

    @Override
    public int getArtistVisits() {
        return artistCount;
    }

    @Override
    public void clearVisits() {
        this.albumCount = 0;
        this.artistCount = 0;
    }

    @Override
    public String onRequestPeekPanel() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String result = "";
        if (authentication != null) {
            result = authentication.getName() + " is peeking under the hood!";
        }

        return result;
    }

    @Override
    public String onRequestDeleteUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String result = "";
        if (authentication != null) {
            result = authentication.getName() + " deleted a user with ID: ";
        }
        return result;
    }
}
