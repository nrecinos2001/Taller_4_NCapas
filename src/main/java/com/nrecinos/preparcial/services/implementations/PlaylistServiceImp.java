package com.nrecinos.preparcial.services.implementations;

import java.rmi.ServerException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrecinos.preparcial.models.dtos.CreatePlaylistDTO;
import com.nrecinos.preparcial.models.dtos.PlaylistWithSongsDTO;
import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.Song;
import com.nrecinos.preparcial.models.entities.SongXPlaylist;
import com.nrecinos.preparcial.models.entities.User;
import com.nrecinos.preparcial.repositories.PlaylistRepository;
import com.nrecinos.preparcial.repositories.SongXPlaylistRepository;
import com.nrecinos.preparcial.services.PlaylistService;
import com.nrecinos.preparcial.services.SongService;
import com.nrecinos.preparcial.utils.*;

import jakarta.transaction.Transactional;

@Service
public class PlaylistServiceImp implements PlaylistService{

	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired SongXPlaylistRepository songXPlaylistRepository;
	
	@Autowired 
	private SongService songService;
	
	private static DurationManager durationManager = new DurationManager();
	
	@Override
	public void saveSongPlaylist(UUID codep, UUID codes) throws ServerException {
		Song song = songService.findSongById(codes);
		Playlist playlist = findPlaylistById(codep);
		
		SongXPlaylist songXPlaylist = songXPlaylistRepository.findBySongAndPlaylist(song, playlist);
		if (songXPlaylist != null) {
			throw new ServerException("Error");
		}
		
		if(song == null || playlist == null) {
			return ;
		}
		Date date = new Date();
		SongXPlaylist newSong = new SongXPlaylist(playlist, song, date);
		songXPlaylistRepository.save(newSong);	
	}
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void savePlaylist(CreatePlaylistDTO playlist, User user) {
		// TODO Auto-generated method stub
		Playlist newPlaylist = new Playlist(
				playlist.getTitle(),
				playlist.getDescription(),
				user
				);
		playlistRepository.save(newPlaylist);
	}

	@Override
	public PlaylistWithSongsDTO findPlaylistWithSongById(UUID code) {
		Playlist playlist = playlistRepository.findById(code).orElse(null);
		Playlist playlistWithSongs = playlistRepository.findPlaylistWithSongs(playlist);
		Duration sumDuration = Duration.ZERO;
		String zeroDuration = "0.00";
		if (playlistWithSongs == null) {
            return new PlaylistWithSongsDTO(playlist, new ArrayList<>(), zeroDuration);
        }
		List<Song> songs = playlistWithSongs.getSongXPlaylist()
                .stream()
                .map(SongXPlaylist::getSong)
                .collect(Collectors.toList());
		
		
		if (songs.isEmpty()) {
            return new PlaylistWithSongsDTO(playlistWithSongs, new ArrayList<>(), zeroDuration);
        }
		
		for(Song song : songs) {
			Duration duration = durationManager.parseDurationString(song.getDuration());
			sumDuration = sumDuration.plus(duration);
		}
		
		String sumDurationString = durationManager.formatDuration(sumDuration);

        PlaylistWithSongsDTO playlistWithSongsDTO = new PlaylistWithSongsDTO(playlistWithSongs, songs, sumDurationString);
		return playlistWithSongsDTO;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deletePlaylist(UUID code) {
		// TODO Auto-generated method stub
		playlistRepository.deleteById(code);
		
	}

	@Override
	public List<Playlist> findAll() {
		// TODO Auto-generated method stub
		return playlistRepository.findAll();
	}

	@Override
	public List<Playlist> findByTitleContains(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsByUserAndTitle(User user, String title) {
		return playlistRepository.existsByUserAndTitle(user, title);
	}
	
	@Override
	public List<Playlist> findByUser(User user, String fragment) {
		if (fragment.isEmpty()) {
			return playlistRepository.findByUser(user);
		}
		return playlistRepository.findByUserAndTitleContains(user, fragment);
	}

	@Override
	public Playlist findPlaylistById(UUID code) {
		return playlistRepository.findByCode(code);
	}

	@Override
	public List<Playlist> finByIdentifier(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}
}
