package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.model.entity.Artist;
import com.yankovltd.tunes.model.entity.Picture;
import com.yankovltd.tunes.model.view.AlbumViewModel;
import com.yankovltd.tunes.model.view.ArtistDetailsViewModel;
import com.yankovltd.tunes.model.view.ArtistViewModel;
import com.yankovltd.tunes.repository.ArtistRepository;
import com.yankovltd.tunes.service.ArtistService;
import com.yankovltd.tunes.service.StatsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final StatsService statsService;
    private final ModelMapper modelMapper;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository, StatsService statsService, ModelMapper modelMapper) {
        this.artistRepository = artistRepository;
        this.statsService = statsService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public Page<ArtistViewModel> showArtists(int pageNumber, String query) {
        if (query != null) {
            List<ArtistViewModel> search = artistRepository.search(query.trim())
                    .stream()
                    .map(a -> modelMapper.map(a, ArtistViewModel.class))
                    .sorted(Comparator.comparing(ArtistViewModel::getName))
                    .collect(Collectors.toList());
            return new PageImpl<>(search);
        }
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 6, sort);
        Page<Artist> artists = artistRepository.findAll(pageable);

        return artists.map(a -> modelMapper.map(a, ArtistViewModel.class));
    }

    @Transactional
    @Override
    public ArtistDetailsViewModel showArtist(Long id) {
        statsService.userArtistVisits();
        return artistRepository.findById(id)
                .stream()
                .map(artist -> {
                    ArtistDetailsViewModel artistDetailsViewModel = new ArtistDetailsViewModel();
                    artistDetailsViewModel.setName(artist.getName());

                    List<Picture> pictures = artist.getPictures();

                    artistDetailsViewModel.setArtistPicture(pictures.get(0).getImageUrl());
                    artistDetailsViewModel.setBioPicture(pictures.get(1).getImageUrl());
                    artistDetailsViewModel.setBio(artist.getBio());

                    List<AlbumViewModel> mappedAlbums = artist.getAlbums()
                            .stream()
                            .map(LikedAlbumServiceImpl::mapToAlbumViewModel)
                            .collect(Collectors.toList());

                    artistDetailsViewModel.setAlbums(mappedAlbums);

                    return artistDetailsViewModel;
                })
                .findFirst()
                .orElse(null);
    }

    @Override
    public Artist findArtistById(Long id) {
        return artistRepository.findById(id).orElse(null);
    }
}
