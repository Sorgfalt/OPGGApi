package com.opgg.opggapi.service;

import com.opgg.opggapi.dto.responseDto.SummonerDTO;

public interface SummonerService {
    SummonerDTO callRiotAPISummonerByName(String summonerName);
}
