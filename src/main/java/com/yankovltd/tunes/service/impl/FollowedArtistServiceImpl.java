package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.model.entity.Artist;
import com.yankovltd.tunes.model.entity.FollowedArtist;
import com.yankovltd.tunes.model.entity.UserEntity;
import com.yankovltd.tunes.model.view.ArtistFavoriteViewModel;
import com.yankovltd.tunes.repository.FollowedArtistRepository;
import com.yankovltd.tunes.repository.UserRepository;
import com.yankovltd.tunes.service.ArtistService;
import com.yankovltd.tunes.service.FollowedArtistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowedArtistServiceImpl implements FollowedArtistService {

    private final ArtistService artistService;
    private final UserRepository userRepository;
    private final FollowedArtistRepository followedArtistRepository;

    public FollowedArtistServiceImpl(ArtistService artistService, UserRepository userRepository, FollowedArtistRepository followedArtistRepository) {
        this.artistService = artistService;
        this.userRepository = userRepository;
        this.followedArtistRepository = followedArtistRepository;
    }

    @Transactional
    @Override
    public void followArtist(Long artistId, AppUser appUser) {
        Long userId = appUser.getId();

        Artist artist = artistService.findArtistById(artistId);
        UserEntity user = userRepository.findById(userId).orElse(null);

        FollowedArtist followedArtist = new FollowedArtist();
        followedArtist.setArtist(artist);
        followedArtist.setUserEntity(user);

        List<FollowedArtist> artistsByUser = followedArtistRepository.findByUserEntityId(userId);

        FollowedArtist containsArtist = artistsByUser
                .stream()
                .filter(fa -> fa.getArtist().getName().equals(followedArtist.getArtist().getName()))
                .findAny()
                .orElse(null);

        if (containsArtist == null) {
            followedArtistRepository.save(followedArtist);
        } else {
            followedArtistRepository.delete(containsArtist);
        }
    }

    @Transactional
    @Override
    public List<ArtistFavoriteViewModel> getAllFollowedArtistsByUser(Long id) {
        List<FollowedArtist> followedArtists = followedArtistRepository
                .findAllByUserEntityId(id);

        return followedArtists
                .stream()
                .map(a -> {
                    ArtistFavoriteViewModel artistFavoriteViewModel = new ArtistFavoriteViewModel();
                    artistFavoriteViewModel.setId(a.getArtist().getId());
                    artistFavoriteViewModel.setName(a.getArtist().getName());
                    artistFavoriteViewModel.setPicture(a.getArtist().getPictures().get(2).getImageUrl());
                    return artistFavoriteViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public FollowedArtist findByArtistId(Long id) {
        return followedArtistRepository.findByArtistId(id).orElse(null);
    }

    @Override
    public void deleteUserFollowedArtists(Long id) {
        List<FollowedArtist> byUserEntityId = followedArtistRepository.findByUserEntityId(id);
        byUserEntityId.forEach(followedArtistRepository::delete);
    }

}
