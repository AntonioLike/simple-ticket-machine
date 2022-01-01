package com.cleverti.simpletrainticketmachine.controllers;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cleverti.simpletrainticketmachine.controllers.dtos.RequestDTO;
import com.cleverti.simpletrainticketmachine.services.StationAutoCompleteService;

@RestController
public class StationAutoCompleteServiceController {
	
	@Resource
	private StationAutoCompleteService stationAutoCompleteService;

	@PostMapping("/")
	List<String> getFilteredStations(@RequestBody RequestDTO requestDTO){
		return stationAutoCompleteService.getPossibleStations(requestDTO.getPreviousStations(),requestDTO.getPreviousWord(),requestDTO.getNewWord());
	}
}
