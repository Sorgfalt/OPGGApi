package com.opgg.opggapi.service;

import com.opgg.opggapi.dto.responseDto.League.LeagueEntryDTO;
import com.opgg.opggapi.dto.responseDto.Summoner.SummonerDTO;
import com.opgg.opggapi.dto.responseDto.Summoner.SummonerInfoDTO;
import java.util.List;

public interface SummonerService {
    SummonerDTO callRiotAPISummonerByName(String summonerName);

    SummonerInfoDTO getSummonerInfo(SummonerDTO apiResult, List<LeagueEntryDTO> leagueEntryRes);
}
