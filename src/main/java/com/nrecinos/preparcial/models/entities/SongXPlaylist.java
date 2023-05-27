package com.nrecinos.preparcial.models.entities;

import java.util.Date;
import java.util.UUID;

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "playlist_code", nullable = false)
	private Playlist playlist;
	
	@Column(name = "date_added")
	private Date date_added;
	
	public SongXPlaylist(Playlist playlist) {
		this.playlist = playlist;
		// this.song = song; TODO: Add Song Entity
	}
}
