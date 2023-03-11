package com.opgg.opggapi.controller;

import com.opgg.opggapi.dto.responseDto.League.LeagueEntryDTO;
import com.opgg.opggapi.dto.responseDto.League.LeagueListDTO;
import com.opgg.opggapi.dto.responseDto.SummonerDTO;
import com.opgg.opggapi.dto.responseDto.payload.RequestResponse;
import com.opgg.opggapi.service.LeagueSerivce;
import com.opgg.opggapi.service.SummonerService;
import java.io.UnsupportedEncodingException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api")
public class SummonerController {

    @Autowired
    private SummonerService summonerService;

    @Autowired
    private LeagueSerivce leagueSerivce;

    @PostMapping(value = "/summonerByName")
    @ResponseBody
    public RequestResponse<?> getSummoner(@RequestParam("summonerName")String summonerName) throws UnsupportedEncodingException {
        try {
            summonerName = summonerName.replaceAll(" ", "%20");

            SummonerDTO apiResult = summonerService.callRiotAPISummonerByName(summonerName);

            String encryptedSummonerId = apiResult.getId();

            List<LeagueEntryDTO> leagueEntryRes = leagueSerivce.getLeagueEntriesForSummoner(encryptedSummonerId);

            String leagueId = leagueEntryRes.get(0).getLeagueId();

            LeagueListDTO LeagueList = leagueSerivce.getLeagueList(leagueId);

            return RequestResponse.of(HttpStatus.OK, RequestResponse.Code.SUCCESS, "success", LeagueList);
        } catch (IllegalArgumentException e) {
            log.error("Invalid input: {}", e.getMessage(), e);
            return RequestResponse.of(HttpStatus.BAD_REQUEST, RequestResponse.Code.FAILED, "bad quest", "");
        } catch (Exception e) {
            log.error("Failed to get summoner info: {}", e.getMessage(), e);
            return RequestResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, RequestResponse.Code.FAILED, "server error", "");
        }
    }
}