package com.nrecinos.preparcial.models.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "song")
public class Song {
	@Id
	@Column(name="code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "duration")
	private String duration;
	
	@OneToMany(mappedBy = "song", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<SongXPlaylist> songXPlaylist;
	
	public Song(String title, String duration) {
		super();
		this.title = title;
		this.duration = duration;
	}
}
