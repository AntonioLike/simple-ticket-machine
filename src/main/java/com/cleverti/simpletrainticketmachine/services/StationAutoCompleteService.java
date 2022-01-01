package com.cleverti.simpletrainticketmachine.services;

import java.util.List;

public interface StationAutoCompleteService {	
	List<String> getPossibleStations(List<String> previousStations, String previousWord, String newWord);
}
