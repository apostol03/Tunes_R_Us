package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.model.entity.Song;
import com.yankovltd.tunes.model.view.SongViewModel;
import com.yankovltd.tunes.repository.SongRepository;
import com.yankovltd.tunes.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    public List<SongViewModel> showSongsOrdered(String name) {
        return songRepository.findAllByAlbumNameOrderById(name)
                .stream()
                .map(s -> {
                    SongViewModel songViewModel = new SongViewModel();
                    songViewModel.setId(s.getId());
                    songViewModel.setName(s.getName());
                    songViewModel.setAlbumId(s.getAlbum().getId());
                    return songViewModel;
                })
                .collect(Collectors.toList());
    }
}
