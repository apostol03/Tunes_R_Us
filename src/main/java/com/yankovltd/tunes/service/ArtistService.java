package com.yankovltd.tunes.service;

import com.yankovltd.tunes.model.entity.Artist;
import com.yankovltd.tunes.model.view.ArtistDetailsViewModel;
import com.yankovltd.tunes.model.view.ArtistFavoriteViewModel;
import com.yankovltd.tunes.model.view.ArtistViewModel;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ArtistService {

    ArtistDetailsViewModel showArtist(Long id);

    Artist findArtistById(Long id);

    Page<ArtistViewModel> showArtists(int pageNumber, String query);
}
