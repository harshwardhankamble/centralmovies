package com.central.book.show.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.show.dto.ShowDto;
import com.central.book.show.dto.ShowScreenTimeDto;
import com.central.book.show.entity.Movie;
import com.central.book.show.entity.Screen;
import com.central.book.show.entity.Show;
import com.central.book.show.entity.ShowScreenTime;
import com.central.book.show.entity.id.ShowScreenTimeId;
import com.central.book.show.enums.ShowStatus;
import com.central.book.show.exception.GlobalExceptionResponseEntity;
import com.central.book.show.service.ShowService;
import com.central.book.show.util.CentralMovieUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/shows")
@Tag(name = "Show")
@Import(GlobalExceptionResponseEntity.class)
public class ShowController {
	
	@Autowired
	private ShowService showService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<ShowDto>> getAllShows() {
		
		return ResponseEntity.ok().body(convertEntityToDtos(showService.getAllShows()));
	}
	
	@GetMapping("/{showId}")
	public ResponseEntity<ShowDto> getShowById(@PathVariable Integer showId) {
		
		return ResponseEntity.ok().body(convertEntityToDto(showService.getShowById(showId)));
	}
	
	@PostMapping
	public void addNewShow(@RequestBody ShowDto showDto) {
		Show show = convertDtoToEntity(showDto);
		
		showService.addNewShow(show);
	}
	
	@DeleteMapping("/{showId}")
	public void removeShow(@PathVariable Integer showId) {
		
		showService.removeShowByShowId(showId);
	}
	
	@PutMapping("/{showId}")
	public void updateShow(@RequestBody ShowDto showDto, @PathVariable Integer showId) {
		Show show = convertDtoToEntity(showDto);
		show.setShowId(showId);
		
		showService.updateShow(show);
	}
	
	@PutMapping("/{showId}/changeShowStatus/{status}")
	public void changeShowStatus(@PathVariable Integer showId, @PathVariable ShowStatus status) {
		
		showService.changeShowStatus(showId, status);
	}
	
	private ShowDto convertEntityToDto(Show show) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ShowDto showDto = modelMapper.map(show, ShowDto.class);
		if (show.getMovie() != null) {
			showDto.setMovieId(show.getMovie().getMovieId());
			showDto.setMovieName(show.getMovie().getMovieName());
		}
		
		showDto.setShowTime(new ArrayList<>());
		
		List<ShowScreenTimeDto> showTime = new ArrayList<>();
		
		for(ShowScreenTime showScreenTime: show.getShowTime()) {
			ShowScreenTimeDto screenTimeDto = new ShowScreenTimeDto();
			screenTimeDto.setScreenId(showScreenTime.getShowScreenTimeId().getScreen().getScreenId());
			screenTimeDto.setShowDateTime(CentralMovieUtil.convertDateToString(showScreenTime.getShowScreenTimeId().getShowDateTime(), "dd-mm-yyyy HH:MM"));
			screenTimeDto.setShowStatus(showScreenTime.getShowStatus());
			showTime.add(screenTimeDto);
		}
		
		showDto.setShowTime(showTime);

		return showDto;
	}
	
	private List<ShowDto> convertEntityToDtos(List<Show> shows) {
		
		return shows.stream().map(this::convertEntityToDto).toList();
	}
	
	private Show convertDtoToEntity(ShowDto showDto) {
		Show show = modelMapper.map(showDto, Show.class);
		
		Movie movie = new Movie();
		movie.setMovieId(showDto.getMovieId());
		show.setMovie(movie);
		
		show.setShowTime(new ArrayList<>());	
		List<ShowScreenTime> showTime = new ArrayList<>();
		
		for(ShowScreenTimeDto showScreenTimeDto: showDto.getShowTime()) {
			ShowScreenTime showScreenTime = new ShowScreenTime();
			ShowScreenTimeId screenTimeId = new ShowScreenTimeId();
			Screen screen = new Screen();
			screen.setScreenId(showScreenTimeDto.getScreenId());
			screenTimeId.setScreen(screen);
			screenTimeId.setShowDateTime(CentralMovieUtil.convertStringToDate(showScreenTimeDto.getShowDateTime(), "dd-mm-yyyy HH:MM"));
			showScreenTime.setShowScreenTimeId(screenTimeId);
			showScreenTime.setShowStatus(showScreenTimeDto.getShowStatus());
			showTime.add(showScreenTime);
		}
		
		show.setShowTime(showTime);
		
		return show;
	}

}
