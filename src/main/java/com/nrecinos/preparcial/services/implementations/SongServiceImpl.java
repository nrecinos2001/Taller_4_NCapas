package com.nrecinos.preparcial.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrecinos.preparcial.models.dtos.CreateSongDTO;
import com.nrecinos.preparcial.models.dtos.UpdateSongTitleDTO;
import com.nrecinos.preparcial.models.entities.Song;
import com.nrecinos.preparcial.repositories.SongRepository;
import com.nrecinos.preparcial.services.SongService;

import jakarta.transaction.Transactional;

@Service
public class SongServiceImpl implements SongService{

	@Autowired
	private SongRepository songRepository;
		
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void saveSong(CreateSongDTO song) throws Exception {
		// TODO Auto-generated method stub
		Song newSong = new Song( 
				
				song.getTitle(),
				song.getDuration()
				);
		
		songRepository.save(newSong);
		
	}

	@Override
	public Song findOneById(UUID code){
		return songRepository.findById(code).orElse(null);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteSongById(UUID code) {
		songRepository.deleteById(code);
		
	}

	@Override
	public void updateTitle(UpdateSongTitleDTO title, Song actualSong) {
		// TODO Auto-generated method stub
		Song modifiedSong = actualSong;
		modifiedSong.setTitle(title.getNewTitle());
		songRepository.save(modifiedSong);
		
	}

	@Override
	public List<Song> findAll() {
		// TODO Auto-generated method stub
		return songRepository.findAll();
	}
	
}
