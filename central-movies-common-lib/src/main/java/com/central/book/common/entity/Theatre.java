package com.central.book.common.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "theatres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Theatre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer theatreId;
	
	private String theatreName;
	
	private String location;
	
	@OneToMany(mappedBy = "theatre")
	private List<Screen> screens = new ArrayList<>();
}
