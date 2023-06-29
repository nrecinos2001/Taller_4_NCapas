package com.nrecinos.preparcial.services.implementations;

import java.util.List;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nrecinos.preparcial.models.entities.Song;
import com.nrecinos.preparcial.repositories.SongRepository;
import com.nrecinos.preparcial.services.SongService;


@Service
public class SongServiceImpl implements SongService{

	@Autowired
	private SongRepository songRepository;
	
	public Song findSongById(UUID code) {
		return songRepository.findByCode(code);
	}
	
	@Override
	public Page<Song> findAll(String fragment, Integer page, Integer size){
		Pageable pageable = PageRequest.of(page, size);
				if (fragment.isEmpty()) {
					return songRepository.findAll(pageable);
				} else {			
					return songRepository.findByTitleContaining(fragment, pageable);
				}
	}

	@Override
	public List<Song> findAll(String fragment) {
		// TODO Auto-generated method stub
		return null;
	}
}
