package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.model.entity.Album;
import com.yankovltd.tunes.model.entity.Artist;
import com.yankovltd.tunes.model.entity.Picture;
import com.yankovltd.tunes.model.entity.Song;
import com.yankovltd.tunes.model.entity.enums.GenreEnum;
import com.yankovltd.tunes.model.view.AlbumDetailsViewModel;
import com.yankovltd.tunes.model.view.AlbumViewModel;
import com.yankovltd.tunes.repository.AlbumRepository;
import com.yankovltd.tunes.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumServiceImplTest {

    private AlbumService serviceToTest;

    @Mock
    private AlbumRepository mockAlbumRepository;

    @Mock
    private StatsServiceImpl statsService;

    @BeforeEach
    void setUp() {
        serviceToTest = new AlbumServiceImpl(mockAlbumRepository, statsService);
    }

    @Test
    void showHomeAlbumsShouldWork() {
        // Arrange
        Album album = new Album();
        album
                .setName("Azis")
                .setArtist(new Artist().setName("Azis"))
                .setGenre(GenreEnum.POP)
                .setPicture(new Picture().setName("1"))
                .setYearReleased(1);

        when(mockAlbumRepository.findAllBy())
                .thenReturn(List.of(album));

        // act
        List<AlbumViewModel> albumViewModels = serviceToTest.showHomeAlbums();

        // assert
        assertEquals(1, albumViewModels.size());
        AlbumViewModel actualView = albumViewModels.get(0);

        assertEquals(album.getName(), actualView.getName());
        assertEquals(album.getGenre().toString(), actualView.getGenre());
        assertEquals(album.getYearReleased(), actualView.getYearReleased());
    }

    @Test
    void showAlbumShouldWork() {
        Album album = new Album();
        album
                .setName("Azis")
                .setArtist(new Artist().setName("Azis"))
                .setGenre(GenreEnum.POP)
                .setPicture(new Picture().setName("1"))
                .setYearReleased(1)
                .setSongs(List.of(new Song().setName("Ratata")));

        when(mockAlbumRepository.findById(album.getId()))
                .thenReturn(Optional.of(album));

        AlbumDetailsViewModel actualView = serviceToTest.showAlbum(album.getId());
        assertEquals(album.getName(), actualView.getName());
    }

    @Test
    void showAlbumShouldReturnNull() {
        AlbumDetailsViewModel view = serviceToTest.showAlbum(null);
        assertNull(view);
    }
}