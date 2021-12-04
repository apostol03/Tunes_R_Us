package com.yankovltd.tunes.service;

import com.yankovltd.tunes.model.entity.Song;
import com.yankovltd.tunes.model.view.SongViewModel;

import java.util.List;
import java.util.Set;

public interface SongService {

    Song findById(Long id);

    List<SongViewModel> showSongsOrdered(String name);
}
