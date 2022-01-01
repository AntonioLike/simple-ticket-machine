package com.cleverti.simpletrainticketmachine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.cleverti.simpletrainticketmachine.repositories.StationRepository;
import com.cleverti.simpletrainticketmachine.repositories.entities.Station;
import com.cleverti.simpletrainticketmachine.services.impl.StationAutoCompleteServiceImpl;

@RunWith(SpringRunner.class)
public class StationAutoCompleteServiceImplTest {

	@Mock
	private StationRepository stationRepository;
	
	@InjectMocks
	private StationAutoCompleteServiceImpl stationAutoCompleteService;
	
	private List<Station> stations;
	private List<String> stationsNames;
	
	@Before
	public void setUp() {
		this.stationsNames = Arrays.asList("Aeroporto","Alameda","Alfornelos","Alto dos Moinhos","Alvalade","Amadora Este","Ameixoeira","Anjos","Areeiro","Arroios","Avenida","Baixa-Chiado","Bela Vista","Cabo Ruivo","Cais do Sodré","Campo Grande","Campo Pequeno","Carnide","Chelas","Cidade Universitária","Colégio Militar / Luz","Encarnação","Entre Campos","Intendente","Jardim Zoológico","Laranjeiras","Lumiar","Marquês de Pombal","Martim Moniz","Moscavide","Odivelas","Olaias","Olivais","Oriente","Parque","Picoas","Pontinha","Praça de Espanha","Quinta das Conchas","Rato","Reboleira","Restauradores","Roma","Rossio","Saldanha","Santa Apolónia","São Sebastião","Senhor Roubado","Telheiras","Terreiro do Paço");
		this.stations = stationsNames.stream().map(name->Station.builder().name(name).build()).collect(Collectors.toList());
		when(this.stationRepository.findAll()).thenReturn(this.stations); 	
	}
	
	@Test
	public void should_return_all_stations_WHEN_newWord_is_empty() {
		List<String> filteredStations;
		
		filteredStations = stationAutoCompleteService.getPossibleStations(null, null, null);
		for(int i=0;i<this.stationsNames.size();i++)
			assertEquals(stationsNames.get(i), filteredStations.get(i));
		
		filteredStations = stationAutoCompleteService.getPossibleStations(new ArrayList<>(), null, null);
		for(int i=0;i<this.stationsNames.size();i++)
			assertEquals(stationsNames.get(i), filteredStations.get(i));
		
		filteredStations = stationAutoCompleteService.getPossibleStations(new ArrayList<>(), "randomString", null);
		for(int i=0;i<this.stationsNames.size();i++)
			assertEquals(stationsNames.get(i), filteredStations.get(i));
	}
	
	@Test
	public void should_not_call_repository_AND_filter_list_stations_WHEN_newWord_starts_with_previousWord() {		
		final String previousWord = "A";
		final String newWord = "Al";
		
		final List<String> previousStations = this.stationsNames.stream().filter(name->name.startsWith(previousWord)).collect(Collectors.toList()); 
		
		final List<String> expectedResult = stationsNames.stream().filter(name->name.startsWith(newWord)).collect(Collectors.toList());
		
		final List<String> filteredStations = this.stationAutoCompleteService.getPossibleStations(previousStations, previousWord, newWord);
		
		
		verify(this.stationRepository,times(0)).findAll();
		for(int i=0;i<expectedResult.size();i++)
			assertEquals(expectedResult.get(i), filteredStations.get(i));
	}
	
	@Test
	public void should_call_repository_AND_filter_list_stations_WHEN_newWord_does_not_start_with_previousWord() {		
		final String previousWord = "B";
		final String newWord = "Al";
		
		final List<String> previousStations = this.stationsNames.stream().filter(name->name.startsWith(previousWord)).collect(Collectors.toList()); 
		
		final List<String> expectedResult = stationsNames.stream().filter(name->name.startsWith(newWord)).collect(Collectors.toList());
		
		final List<String> filteredStations = this.stationAutoCompleteService.getPossibleStations(previousStations, previousWord, newWord);
		
		
		verify(this.stationRepository,times(1)).findAll();
		for(int i=0;i<expectedResult.size();i++)
			assertEquals(expectedResult.get(i), filteredStations.get(i));
	}
	
	
}
