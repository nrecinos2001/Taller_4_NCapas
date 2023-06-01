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
	public List<Song> findAll(String fragment) {
		// TODO Auto-generated method stub
		if (fragment.isEmpty()) {
			return songRepository.findAll();
		}
		return songRepository.findByTitleContaining(fragment);
	}
}
