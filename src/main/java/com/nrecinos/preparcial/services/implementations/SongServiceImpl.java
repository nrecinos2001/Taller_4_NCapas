package com.nrecinos.preparcial.services.implementations;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrecinos.preparcial.models.entities.Song;
import com.nrecinos.preparcial.repositories.SongRepository;
import com.nrecinos.preparcial.services.SongService;


@Service
public class SongServiceImpl implements SongService{

	@Autowired
	private SongRepository songRepository;
  
	@Override
	public List<Song> findAll(String fragment) {
		// TODO Auto-generated method stub
		if (fragment.isEmpty()) {
			return songRepository.findAll();
		} else {			
			return songRepository.findByTitleContaining(fragment);
		}
	}
	
	public Song findSongById(UUID code) {
		return songRepository.findByCode(code);
	}
	
	@Override
	public Page<Song> findAll(Int page, Int size){
		Pageable pageable = PageRequest.of(page, size);
		return songRepository.findAll(pageable)
	}
}
