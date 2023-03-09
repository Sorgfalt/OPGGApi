package com.opgg.opggapi.controller;

import com.opgg.opggapi.dto.responseDto.LeagueEntriesDto;
import com.opgg.opggapi.dto.responseDto.SummonerDto;
import com.opgg.opggapi.dto.responseDto.payload.RequestResponse;
import com.opgg.opggapi.service.SummonerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api")
public class SummonerController {

    @Autowired
    private SummonerService summonerService;

    @PostMapping(value = "/summonerByName")
    @ResponseBody
    public RequestResponse<?> getSummoner(String summonerName) {
        summonerName = summonerName.replaceAll(" ","%20");

        SummonerDto apiResult = summonerService.callRiotAPISummonerByName(summonerName);

        System.out.println("apiResult = " + apiResult);

        String id = apiResult.getId();

        System.out.println("id = " + id);

        List<LeagueEntriesDto> leagueEntriesRes = summonerService.getLeagueEntriesForSummoner(id);

        System.out.println("leagueEntriesRes = " + leagueEntriesRes);

        return RequestResponse.of(HttpStatus.OK, RequestResponse.Code.SUCCESS, "it's ok", leagueEntriesRes);
    }
}