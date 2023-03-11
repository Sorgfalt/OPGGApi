package com.opgg.opggapi.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opgg.opggapi.dto.responseDto.SummonerDTO;
import com.opgg.opggapi.service.SummonerService;
import com.opgg.opggapi.util.CallRiotApi;
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
}
