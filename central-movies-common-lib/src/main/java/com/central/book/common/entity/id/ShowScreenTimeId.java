package com.central.book.common.entity.id;

import java.util.Date;

import com.central.book.common.entity.Screen;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Embeddable
@Data
public class ShowScreenTimeId {
	
	@OneToOne
	@JoinColumn(name = "screenId")
	private Screen screen;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date showDateTime;

}
