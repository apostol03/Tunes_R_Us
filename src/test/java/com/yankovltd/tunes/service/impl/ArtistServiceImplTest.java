package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.model.entity.Album;
import com.yankovltd.tunes.model.entity.Artist;
import com.yankovltd.tunes.model.entity.Picture;
import com.yankovltd.tunes.model.entity.enums.GenreEnum;
import com.yankovltd.tunes.model.view.ArtistDetailsViewModel;
import com.yankovltd.tunes.repository.ArtistRepository;
import com.yankovltd.tunes.service.ArtistService;
import com.yankovltd.tunes.service.StatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistServiceImplTest {

    private ArtistService serviceToTest;

    @Mock
    private StatsService statsService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ArtistRepository mockArtistRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new ArtistServiceImpl(mockArtistRepository, statsService, modelMapper);
    }

    @Test
    void showArtistShouldWork() {
        Artist artist = new Artist();
        Album album = new Album();
        album.setName("bla")
                .setArtist(artist)
                .setGenre(GenreEnum.EDM)
                .setPicture(new Picture().setName("bla"))
                .setYearReleased(2);
        artist
                .setName("Azis")
                .setBio("cool")
                .setAlbums(List.of(album))
                .setPictures(List.of(new Picture().setName("bla"), new Picture().setName("oo")));

        when(mockArtistRepository.findById(artist.getId()))
                .thenReturn(Optional.of(artist));

        ArtistDetailsViewModel actualView = serviceToTest.showArtist(artist.getId());
        assertEquals(artist.getName(), actualView.getName());
    }

    @Test
    void showArtistShouldReturnNull() {
        assertNull(serviceToTest.showArtist(null));
    }
}