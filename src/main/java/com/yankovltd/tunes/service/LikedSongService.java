package com.yankovltd.tunes.service;

import com.yankovltd.tunes.model.view.SongFavoriteViewModel;
import com.yankovltd.tunes.service.impl.AppUser;

import java.util.List;

public interface LikedSongService {

    void likeSong(Long id, AppUser appUser);

    void unlikeSong(Long id, AppUser appUser);

    List<SongFavoriteViewModel> getAllLikedSongsByUser(Long id);

    void deleteUserLikedSongs(Long id);
}
