package com.nrecinos.preparcial.repositories;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.nrecinos.preparcial.models.dtos.PlaylistWithSongsDTO;
import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.Song;
import com.nrecinos.preparcial.models.entities.SongXPlaylist;

public interface SongXPlaylistRepository extends JpaRepository<SongXPlaylist, UUID>{
	SongXPlaylist findBySongAndPlaylist(Song song, Playlist playlit);
	@Query("SELECT new com.nrecinos.preparcial.models.dtos.PlaylistWithSongsDTO(p, s) FROM Playlist p JOIN p.songXPlaylist sp JOIN sp.song s WHERE p = :playlist")
    PlaylistWithSongsDTO findPlaylistWithSongs(@Param("playlist") Playlist playlist);
	SongXPlaylist findByPlaylist(Playlist playlist);
}
 