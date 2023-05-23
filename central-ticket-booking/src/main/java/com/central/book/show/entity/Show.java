package com.central.book.show.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "theatre_shows")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer showId;

	private String showName;
	
	@ManyToOne
	@JoinColumn(name = "movieId")
	private Movie movie;

	private Date startingDate;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "show", fetch = FetchType.EAGER)
	private List<ShowScreenTime> showTime = new ArrayList<>();

	@Transient
	private Integer movieId;
	
}
