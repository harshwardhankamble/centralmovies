package com.central.book.common.entity;

import java.util.HashSet;
import java.util.Set;

import com.central.book.common.entity.id.ShowScreenTimeId;
import com.central.book.common.enums.ShowStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "show_screen_time")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShowScreenTime {
	
	@EmbeddedId
	private ShowScreenTimeId showScreenTimeId;
	
	@Enumerated(EnumType.ORDINAL)
	private ShowStatus showStatus;
	
	@ManyToOne
	@JoinColumn(name = "showId")
	private Show show;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "showScreenTime")
	private Set<Seat> seats = new HashSet<>();

}
