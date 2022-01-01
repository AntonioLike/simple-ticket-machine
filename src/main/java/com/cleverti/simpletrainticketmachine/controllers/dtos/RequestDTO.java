package com.cleverti.simpletrainticketmachine.controllers.dtos;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestDTO {
	private List<String> previousStations;
	private String previousWord;
	private String newWord;
}
