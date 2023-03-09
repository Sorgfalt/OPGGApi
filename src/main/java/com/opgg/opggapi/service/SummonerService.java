package com.opgg.opggapi.service;

import com.opgg.opggapi.dto.responseDto.LeagueEntriesDto;
import com.opgg.opggapi.dto.responseDto.SummonerDto;

import java.util.List;
import java.util.Map;

public interface SummonerService {
    SummonerDto callRiotAPISummonerByName(String summonerName);

    List<LeagueEntriesDto> getLeagueEntriesForSummoner(String id);
}
