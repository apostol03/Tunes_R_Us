package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.model.entity.Album;
import com.yankovltd.tunes.model.entity.enums.GenreEnum;
import com.yankovltd.tunes.model.view.AlbumDetailsViewModel;
import com.yankovltd.tunes.model.view.AlbumViewModel;
import com.yankovltd.tunes.repository.AlbumRepository;
import com.yankovltd.tunes.service.AlbumService;
import com.yankovltd.tunes.service.StatsService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.yankovltd.tunes.service.impl.LikedAlbumServiceImpl.mapToAlbumViewModel;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final StatsService statsService;

    public AlbumServiceImpl(AlbumRepository albumRepository, StatsService statsService) {
        this.albumRepository = albumRepository;
        this.statsService = statsService;
    }

    @Transactional
    @Override
    public Page<AlbumViewModel> showAlbums(int pageNumber, String query,
                                           String sortType) {
        if (query != null) {
            List<AlbumViewModel> search = albumRepository.search(query.trim())
                    .stream()
                    .map(AlbumServiceImpl::albumMap)
                    .sorted(Comparator.comparing(AlbumViewModel::getName))
                    .collect(Collectors.toList());

            return new PageImpl<>(search);
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, 18,
                Sort.by("name").ascending());
        Pageable pageableDesc = PageRequest.of(pageNumber - 1, 18,
                Sort.by("name").descending());
        Pageable pageableYear = PageRequest.of(pageNumber - 1, 18,
                Sort.by("yearReleased").ascending());
        Pageable pageableYearDesc = PageRequest.of(pageNumber - 1, 18,
                Sort.by("yearReleased").descending());
        Pageable pageableArtist = PageRequest.of(pageNumber - 1, 18,
                Sort.by("artist").ascending());
        Pageable pageableArtistDesc = PageRequest.of(pageNumber - 1, 18,
                Sort.by("artist").descending());


        Page<Album> albums = switch (sortType) {
            case "Year" -> albumRepository.findAll(pageableYear);
            case "Year(Desc)" -> albumRepository.findAll(pageableYearDesc);
            case "Artist" -> albumRepository.findAll(pageableArtist);
            case "Artist(Desc)" -> albumRepository.findAll(pageableArtistDesc);
            case "Name(Desc)" -> albumRepository.findAll(pageableDesc);
            default -> albumRepository.findAll(pageable);
        };
        return albums.map(AlbumServiceImpl::albumMap);
    }

    @Transactional
    @Override
    public List<AlbumViewModel> showHomeAlbums() {
        List<Album> all = albumRepository.findAllBy();
        List<AlbumViewModel> albums = all
                .stream()
                .map(AlbumServiceImpl::albumMap)
                .collect(Collectors.toList());
        Collections.shuffle(albums);
        return albums.stream().limit(3).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public AlbumDetailsViewModel showAlbum(Long id) {
        statsService.userAlbumVisits();
        return albumRepository.findById(id)
                .stream()
                .map(album -> {
                    AlbumDetailsViewModel albumDetailsViewModel = new AlbumDetailsViewModel();
                    albumDetailsViewModel.setId(album.getId());
                    albumDetailsViewModel.setName(album.getName());
                    setCorrectGenre(album, albumDetailsViewModel);
                    albumDetailsViewModel.setYearReleased(album.getYearReleased());
                    albumDetailsViewModel.setPicture(album.getPicture().getImageUrl());
                    albumDetailsViewModel.setSongsCount(album.getSongs().size());
                    albumDetailsViewModel.setSongs(album.getSongs());
                    albumDetailsViewModel.setArtist(album.getArtist());
                    return albumDetailsViewModel;
                })
                .findFirst()
                .orElse(null);
    }

    @Override
    public Album findById(Long id) {
        return albumRepository.findById(id).orElse(null);
    }

    private void setCorrectGenre(Album album, AlbumDetailsViewModel albumDetailsViewModel) {
        GenreEnum genre = album.getGenre();
        switch (genre) {
            case HIP_HOP -> albumDetailsViewModel.setGenre("Hip Hop");
            case POP -> albumDetailsViewModel.setGenre("R&B");
            case EDM -> albumDetailsViewModel.setGenre("EDM");
            default -> {
                String all = genre.toString().toLowerCase();
                String fL = genre.toString().substring(0, 1).toUpperCase();
                albumDetailsViewModel.setGenre(all + fL);
            }
        }
    }

    private static AlbumViewModel albumMap(Album album) {
        return mapToAlbumViewModel(album);
    }
}
