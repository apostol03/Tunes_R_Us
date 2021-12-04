package com.yankovltd.tunes.service;

import com.yankovltd.tunes.model.entity.LikedAlbum;
import com.yankovltd.tunes.model.view.AlbumViewModel;
import com.yankovltd.tunes.service.impl.AppUser;

import java.util.List;

public interface LikedAlbumService {

    void likeAlbum(Long id, AppUser appUser);

    List<AlbumViewModel> getAllLikedAlbumsByUser(Long id);

    LikedAlbum findByAlbumId(Long albumId);

    void deleteUserLikedAlbums(Long id);
}
