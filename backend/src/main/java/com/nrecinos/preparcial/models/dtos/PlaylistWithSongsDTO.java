package com.nrecinos.preparcial.models.dtos;

import java.util.List;

import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.Song;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaylistWithSongsDTO {
    private Playlist playlist;
    private List<Song> songs;
    private String duration;

    public PlaylistWithSongsDTO(Playlist playlist, List<Song> songs, String duration) {
        this.playlist = playlist;
        this.songs = songs;
        this.duration = duration;
    }
}
