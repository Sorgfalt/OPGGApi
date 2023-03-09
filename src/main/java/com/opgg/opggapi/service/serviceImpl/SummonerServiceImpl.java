package com.opgg.opggapi.service.serviceImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opgg.opggapi.dto.responseDto.LeagueEntriesDto;
import com.opgg.opggapi.dto.responseDto.SummonerDto;
import com.opgg.opggapi.service.SummonerService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SummonerServiceImpl implements SummonerService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${api.url}")
    String apiUrl = "";

    @Value("${path.riotGames.api.key}")
    String myKey = "";

    @Override
    public SummonerDto callRiotAPISummonerByName(String summonerName) {
        SummonerDto result;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(apiUrl + "/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + myKey);

            System.out.println("request = " + request);
            HttpResponse response = client.execute(request);

            if(response.getStatusLine().getStatusCode() != 200){
                return null;
            }

            HttpEntity entity = response.getEntity();
            result = objectMapper.readValue(entity.getContent(), SummonerDto.class);

        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    public List<LeagueEntriesDto> getLeagueEntriesForSummoner(String id) {
        List<LeagueEntriesDto> result;

        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(apiUrl + "/lol/league/v4/entries/by-summoner/" + id + "?api_key=" + myKey);

            System.out.println("request = " + request);
            HttpResponse response = client.execute(request);

            if(response.getStatusLine().getStatusCode() != 200){
                return null;
            }

            HttpEntity entity = response.getEntity();
            result = objectMapper.readValue(entity.getContent(), new TypeReference<List<LeagueEntriesDto>>(){});

        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

        return result;
    }
}
