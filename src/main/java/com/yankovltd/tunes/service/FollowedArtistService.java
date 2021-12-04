package com.yankovltd.tunes.service;

import com.yankovltd.tunes.model.entity.FollowedArtist;
import com.yankovltd.tunes.model.view.ArtistFavoriteViewModel;
import com.yankovltd.tunes.service.impl.AppUser;

import java.util.List;

public interface FollowedArtistService {

    void followArtist(Long id, AppUser appUser);

    List<ArtistFavoriteViewModel> getAllFollowedArtistsByUser(Long id);

    FollowedArtist findByArtistId(Long id);

    void deleteUserFollowedArtists(Long id);
}
