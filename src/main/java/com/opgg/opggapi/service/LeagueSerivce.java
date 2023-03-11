package com.opgg.opggapi.service;

import com.opgg.opggapi.dto.responseDto.League.LeagueEntryDTO;
import com.opgg.opggapi.dto.responseDto.League.LeagueListDTO;
import java.util.List;

public interface LeagueSerivce {
	List<LeagueEntryDTO> getLeagueEntriesForSummoner(String encryptedSummonerId);

	LeagueListDTO getLeagueList(String leagueId);
}
