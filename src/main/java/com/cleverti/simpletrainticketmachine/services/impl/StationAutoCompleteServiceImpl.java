package com.cleverti.simpletrainticketmachine.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.cleverti.simpletrainticketmachine.repositories.StationRepository;
import com.cleverti.simpletrainticketmachine.repositories.entities.Station;
import com.cleverti.simpletrainticketmachine.services.StationAutoCompleteService;

@Service
public class StationAutoCompleteServiceImpl implements StationAutoCompleteService {

	@Resource
	private StationRepository stationRepository;
	
	@Override
	public List<String> getPossibleStations(final List<String>previousStations, final String previousWord, final String newWord) {
		
		if(ObjectUtils.isEmpty(newWord)) {
			return this.getAllStations();
		}
		
		if(previousStations==null||ObjectUtils.isEmpty(previousWord)||!newWord.startsWith(previousWord))
			return filterStations(this.getAllStations(), newWord);
		else
			return filterStations(previousStations, newWord);
	}


	private List<String> filterStations(List<String> stations, String newWord) { 		
		return stations.stream().filter(t -> t.startsWith(newWord)).collect(Collectors.toList());		
	}


	private List<String> getAllStations(){
		return stationRepository.findAll().stream().map(Station::getName).collect(Collectors.toList());
	}
		
}
