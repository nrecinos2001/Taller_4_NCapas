package com.nrecinos.preparcial.models.entities;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.jpa.repository.Temporal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "songxplaylist")
public class SongXPlaylist {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID code;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "playlist_code", nullable = false)
	private Playlist playlist;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "song_code", nullable = false)
	private Song song;
	
	@Column(name = "date_added")
	//@Temporal(TemporalType.TIMESTAMP)
	private Date date_added;
	
	public SongXPlaylist(Playlist playlist, Song song, Date date_added) {
		this.playlist = playlist;
		this.song = song;
		this.date_added = date_added;
	}
}
