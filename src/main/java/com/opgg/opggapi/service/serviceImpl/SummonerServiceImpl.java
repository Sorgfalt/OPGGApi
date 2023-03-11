package com.opgg.opggapi.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opgg.opggapi.dto.responseDto.League.LeagueEntryDTO;
import com.opgg.opggapi.dto.responseDto.Summoner.SummonerDTO;
import com.opgg.opggapi.dto.responseDto.Summoner.SummonerInfoDTO;
import com.opgg.opggapi.service.SummonerService;
import com.opgg.opggapi.util.CallRiotApi;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SummonerServiceImpl implements SummonerService {

    @Autowired
    private CallRiotApi callRiotApi;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${api.url}")
    String apiUrl = "";

    @Value("${path.riotGames.api.key}")
    String myKey = "";

    @Override
    public SummonerDTO callRiotAPISummonerByName(String summonerName) {

        return callRiotApi.callRiotAPISummonerByName(summonerName);
    }

    @Override
    public SummonerInfoDTO getSummonerInfo(SummonerDTO apiResult, List<LeagueEntryDTO> leagueEntryRes) {
        SummonerInfoDTO summonerInfoRes = new SummonerInfoDTO();

        summonerInfoRes.setProfileIconId(apiResult.getProfileIconId());
        summonerInfoRes.setSummonerLevel(apiResult.getSummonerLevel());
        summonerInfoRes.setName(apiResult.getName());
        summonerInfoRes.setQueueType(leagueEntryRes.get(0).getQueueType());
        summonerInfoRes.setTier(leagueEntryRes.get(0).getTier());
        summonerInfoRes.setLeaguePoints(leagueEntryRes.get(0).getLeaguePoints());
        summonerInfoRes.setWins(leagueEntryRes.get(0).getWins());
        summonerInfoRes.setLosses(leagueEntryRes.get(0).getLosses());

        return summonerInfoRes;
    }
}
