package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.model.entity.Album;
import com.yankovltd.tunes.model.entity.LikedSong;
import com.yankovltd.tunes.model.entity.Song;
import com.yankovltd.tunes.model.entity.UserEntity;
import com.yankovltd.tunes.model.view.AlbumViewModel;
import com.yankovltd.tunes.model.view.SongFavoriteViewModel;
import com.yankovltd.tunes.repository.LikedSongRepository;
import com.yankovltd.tunes.repository.UserRepository;
import com.yankovltd.tunes.service.LikedSongService;
import com.yankovltd.tunes.service.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikedSongServiceImpl implements LikedSongService {

    private final LikedSongRepository likedSongRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final SongService songService;

    public LikedSongServiceImpl(LikedSongRepository likedSongRepository, ModelMapper modelMapper, UserRepository userRepository, SongService songService) {
        this.likedSongRepository = likedSongRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.songService = songService;
    }

    @Transactional
    @Override
    public void likeSong(Long id, AppUser appUser) {
        Long userId = appUser.getId();

        Song song = songService.findById(id);
        UserEntity user = userRepository.findById(userId).orElse(null);

        LikedSong likedSong = new LikedSong();
        likedSong.setUserEntity(user);
        likedSong.setSong(song);

        List<LikedSong> likedSongsByUser = likedSongRepository.findByUserEntityId(userId);

        LikedSong containsSong = likedSongsByUser
                .stream()
                .filter(ls -> ls.getSong().getName().equals(likedSong.getSong().getName()))
                .findAny()
                .orElse(null);

        if (containsSong == null) {
            likedSongRepository.save(likedSong);
        } else {
            likedSongRepository.delete(containsSong);
        }
    }

    @Override
    public void unlikeSong(Long id, AppUser appUser) {
        List<LikedSong> userLikedSongs = likedSongRepository.findAllByUserEntityId(appUser.getId());
        userLikedSongs.stream()
                .filter(ul -> ul.getSong().getId().equals(id))
                .findFirst()
                .ifPresent(likedSongRepository::delete);
    }

    @Transactional
    @Override
    public List<SongFavoriteViewModel> getAllLikedSongsByUser(Long id) {
        return likedSongRepository.findAllByUserEntityId(id)
                .stream()
                .map(s -> {
                    SongFavoriteViewModel songFavoriteViewModel = new SongFavoriteViewModel();
                    songFavoriteViewModel.setId(s.getSong().getId());
                    songFavoriteViewModel.setName(s.getSong().getName());
                    songFavoriteViewModel.setArtist(s.getSong().getArtist().getName());
                    Album album = s.getSong().getAlbum();
                    AlbumViewModel mappedAlbum = modelMapper.map(album, AlbumViewModel.class);
                    songFavoriteViewModel.setAlbum(mappedAlbum);
                    return songFavoriteViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserLikedSongs(Long id) {
        List<LikedSong> byUserEntityId = likedSongRepository.findByUserEntityId(id);
        byUserEntityId.forEach(likedSongRepository::delete);
    }
}
