package com.yankovltd.tunes.service;

import com.yankovltd.tunes.model.entity.Album;
import com.yankovltd.tunes.model.view.AlbumDetailsViewModel;
import com.yankovltd.tunes.model.view.AlbumViewModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AlbumService {

    Page<AlbumViewModel> showAlbums(int pageNumber, String query, String sortType);

    List<AlbumViewModel> showHomeAlbums();

    AlbumDetailsViewModel showAlbum(Long id);

    Album findById(Long id);
}
