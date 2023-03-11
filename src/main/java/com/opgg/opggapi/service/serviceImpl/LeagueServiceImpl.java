package com.opgg.opggapi.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opgg.opggapi.dto.responseDto.League.LeagueEntryDTO;
import com.opgg.opggapi.dto.responseDto.League.LeagueListDTO;
import com.opgg.opggapi.service.LeagueSerivce;
import com.opgg.opggapi.util.CallRiotApi;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LeagueServiceImpl implements LeagueSerivce {

	@Autowired
	private CallRiotApi callRiotApi;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Value("${api.url}")
	String apiUrl = "";

	@Value("${path.riotGames.api.key}")
	String myKey = "";

	@Override
	public List<LeagueEntryDTO> getLeagueEntriesForSummoner(String encryptedSummonerId) {
		return callRiotApi.getLeagueEntriesForSummoner(encryptedSummonerId);
	}

	@Override
	public LeagueListDTO getLeagueList(String leagueId) {
		return callRiotApi.getLeagueList(leagueId);
	}
}
