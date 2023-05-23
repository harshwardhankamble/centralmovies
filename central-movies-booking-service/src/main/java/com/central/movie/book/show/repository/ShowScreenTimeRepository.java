package com.central.movie.book.show.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.central.book.common.entity.ShowScreenTime;
import com.central.book.common.entity.id.ShowScreenTimeId;

public interface ShowScreenTimeRepository extends JpaRepository<ShowScreenTime, ShowScreenTimeId>{

	@Modifying
	@Query(value = "update show_screen_time set show_status = :showStatus where show_id = :showId", nativeQuery = true)
	public void changeShowStatus(@Param("showId") Integer showId, @Param("showStatus") Integer showStatus);
	
	public ShowScreenTime findByShowScreenTimeIdScreenScreenIdAndShowScreenTimeIdShowDateTime(Integer screenId, Date showDateTime);
}
