package com.central.movie.theatre.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.common.constant.Constants;
import com.central.book.common.dto.ShowDto;
import com.central.book.common.dto.ShowScreenTimeDto;
import com.central.book.common.entity.Movie;
import com.central.book.common.entity.Screen;
import com.central.book.common.entity.Show;
import com.central.book.common.entity.ShowScreenTime;
import com.central.book.common.entity.id.ShowScreenTimeId;
import com.central.book.common.enums.ShowStatus;
import com.central.book.common.exception.GlobalExceptionResponseEntity;
import com.central.book.common.util.CentralMovieUtil;
import com.central.movie.theatre.aop.AccessControl;
import com.central.movie.theatre.service.ShowService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/shows")
@Tag(name = "Show")
@Import(GlobalExceptionResponseEntity.class)
public class ShowController {
	
	@Autowired
	private ShowService showService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@AccessControl(roles = {Constants.MANAGER, Constants.CUSTOMER, Constants.ADMIN})
	@GetMapping
	public ResponseEntity<List<ShowDto>> getAllShows(@RequestParam Integer userId) {
		
		return ResponseEntity.ok().body(convertEntityToDtos(showService.getAllShows()));
	}
	
	@AccessControl(roles = {Constants.MANAGER, Constants.CUSTOMER, Constants.ADMIN})
	@GetMapping("/{showId}")
	public ResponseEntity<ShowDto> getShowById(@RequestParam Integer userId, @PathVariable Integer showId) {
		
		return ResponseEntity.ok().body(convertEntityToDto(showService.getShowById(showId)));
	}
	
	@AccessControl(roles = {Constants.MANAGER})
	@PostMapping
	public void addNewShow(@RequestParam Integer userId, @RequestBody ShowDto showDto) {
		Show show = convertDtoToEntity(showDto);
		
		showService.addNewShow(show);
	}
	
	@AccessControl(roles = {Constants.MANAGER})
	@DeleteMapping("/{showId}")
	public void removeShow(@RequestParam Integer userId, @PathVariable Integer showId) {
		
		showService.removeShowByShowId(showId);
	}
	
	@AccessControl(roles = {Constants.MANAGER})
	@PutMapping("/{showId}")
	public void updateShow(@RequestParam Integer userId, @RequestBody ShowDto showDto, @PathVariable Integer showId) {
		Show show = convertDtoToEntity(showDto);
		show.setShowId(showId);
		
		showService.updateShow(show);
	}
	
	@AccessControl(roles = {Constants.MANAGER})
	@PutMapping("/{showId}/changeShowStatus/{status}")
	public void changeShowStatus(@RequestParam Integer userId, @PathVariable Integer showId, @PathVariable ShowStatus status) {
		
		showService.changeShowStatus(showId, status);
	}
	
	@AccessControl(roles = {Constants.ADMIN, Constants.MANAGER, Constants.CUSTOMER})
	@GetMapping("/movie/{movieId}")
	public ResponseEntity<List<ShowDto>> getShowsByMovieId(@RequestParam Integer userId, @PathVariable Integer movieId) {
		
		return ResponseEntity.ok(convertEntityToDtos(showService.getShowsByMovieId(movieId)));
	}
	
	private ShowDto convertEntityToDto(Show show) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ShowDto showDto = modelMapper.map(show, ShowDto.class);
		if (show.getMovie() != null) {
			showDto.setMovieId(show.getMovie().getMovieId());
			showDto.setMovieName(show.getMovie().getMovieName());
			showDto.setPosterImageString(show.getMovie().getPosterImage());
		}
		
		showDto.setShowTime(new ArrayList<>());
		
		List<ShowScreenTimeDto> showTime = new ArrayList<>();
		
		for(ShowScreenTime showScreenTime: show.getShowTime()) {
			ShowScreenTimeDto screenTimeDto = new ShowScreenTimeDto();
			screenTimeDto.setScreenId(showScreenTime.getShowScreenTimeId().getScreen().getScreenId());
			screenTimeDto.setScreenName(showScreenTime.getShowScreenTimeId().getScreen().getScreenName());
			screenTimeDto.setShowDateTime(CentralMovieUtil.convertDateToString(showScreenTime.getShowScreenTimeId().getShowDateTime(), "dd-MM-yyyy HH:mm"));
			screenTimeDto.setShowStatus(showScreenTime.getShowStatus());
			showDto.setTheatreName(showScreenTime.getShowScreenTimeId().getScreen().getTheatre().getTheatreName());;
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
			screenTimeId.setShowDateTime(CentralMovieUtil.convertStringToDate(showScreenTimeDto.getShowDate() + ' ' + showScreenTimeDto.getShowTime(), "dd-MM-yyyy HH:mm"));
			showScreenTime.setShowScreenTimeId(screenTimeId);
			showScreenTime.setShowStatus(showScreenTimeDto.getShowStatus());
			showTime.add(showScreenTime);
		}
		
		show.setShowTime(showTime);
		
		return show;
	}

}
