package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.model.entity.Album;
import com.yankovltd.tunes.model.entity.LikedAlbum;
import com.yankovltd.tunes.model.entity.UserEntity;
import com.yankovltd.tunes.model.view.AlbumViewModel;
import com.yankovltd.tunes.repository.LikedAlbumRepository;
import com.yankovltd.tunes.repository.UserRepository;
import com.yankovltd.tunes.service.AlbumService;
import com.yankovltd.tunes.service.LikedAlbumService;
import com.yankovltd.tunes.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikedAlbumServiceImpl implements LikedAlbumService {

    private final UserRepository userRepository;
    private final AlbumService albumService;
    private final LikedAlbumRepository likedAlbumRepository;

    public LikedAlbumServiceImpl(UserRepository userRepository, AlbumService albumService, LikedAlbumRepository likedAlbumRepository) {
        this.userRepository = userRepository;
        this.albumService = albumService;
        this.likedAlbumRepository = likedAlbumRepository;
    }

    @Transactional
    @Override
    public void likeAlbum(Long id, AppUser appUser) {
        Long userId = appUser.getId();

        Album album = albumService.findById(id);
        UserEntity user = userRepository.findById(userId).orElse(null);

        LikedAlbum likedAlbum = new LikedAlbum();
        likedAlbum.setUserEntity(user);
        likedAlbum.setAlbum(album);

        List<LikedAlbum> likedAlbumsByUser = likedAlbumRepository.findByUserEntityId(userId);

        LikedAlbum containsAlbum = likedAlbumsByUser
                .stream()
                .filter(la -> la.getAlbum().getName().equals(likedAlbum.getAlbum().getName()))
                .findAny()
                .orElse(null);

        if (containsAlbum == null) {
            likedAlbumRepository.save(likedAlbum);
        } else {
            likedAlbumRepository.delete(containsAlbum);
        }
    }

    @Transactional
    @Override
    public List<AlbumViewModel> getAllLikedAlbumsByUser(Long id) {
        return likedAlbumRepository.findAllByUserEntityId(id)
                .stream()
                .map(a -> mapToAlbumViewModel(a.getAlbum()))
                .collect(Collectors.toList());
    }

    @Override
    public LikedAlbum findByAlbumId(Long id) {
        return likedAlbumRepository.findByAlbumId(id).orElse(null);
    }

    @Override
    public void deleteUserLikedAlbums(Long id) {
        List<LikedAlbum> byUserEntityId = likedAlbumRepository.findByUserEntityId(id);
        byUserEntityId.forEach(likedAlbumRepository::delete);
    }

    static AlbumViewModel mapToAlbumViewModel(Album album) {
        AlbumViewModel albumViewModel = new AlbumViewModel();
        albumViewModel.setId(album.getId());
        albumViewModel.setName(album.getName());
        albumViewModel.setGenre(album.getGenre().toString());
        albumViewModel.setArtist(album.getArtist().getName());
        albumViewModel.setYearReleased(album.getYearReleased());
        albumViewModel.setPicture(album.getPicture().getImageUrl());
        return albumViewModel;
    }
}
