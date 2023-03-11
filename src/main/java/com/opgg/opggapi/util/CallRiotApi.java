package com.opgg.opggapi.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opgg.opggapi.dto.responseDto.League.LeagueEntryDTO;
import com.opgg.opggapi.dto.responseDto.League.LeagueListDTO;
import com.opgg.opggapi.dto.responseDto.Summoner.SummonerDTO;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CallRiotApi {
	private final Logger logger = LoggerFactory.getLogger(CallRiotApi.class);
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Value("${api.url}")
	String apiUrl = "";

	@Value("${path.riotGames.api.key}")
	String myKey = "";
	public SummonerDTO callRiotAPISummonerByName(String summonerName) {
		SummonerDTO result;
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(apiUrl + "/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + myKey);
			logger.debug("HTTP request: {}", request);

			HttpResponse response = client.execute(request);

			if(response.getStatusLine().getStatusCode() != 200){
				logger.error("HTTP request failed with status code: {}", response.getStatusLine().getStatusCode());
				return null;
			}

			HttpEntity entity = response.getEntity();
			result = objectMapper.readValue(entity.getContent(), SummonerDTO.class);

		} catch (IOException e){
			logger.error("HTTP request failed with exception", e);
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public List<LeagueEntryDTO> getLeagueEntriesForSummoner(String encryptedSummonerId) {
		List<LeagueEntryDTO> result;

		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(apiUrl + "/lol/league/v4/entries/by-summoner/" + encryptedSummonerId + "?api_key=" + myKey);

			logger.debug("HTTP request: {}", request);
			HttpResponse response = client.execute(request);

			if(response.getStatusLine().getStatusCode() != 200){
				logger.error("HTTP request failed with status code: {}", response.getStatusLine().getStatusCode());
				return null;
			}

			HttpEntity entity = response.getEntity();

			result = objectMapper.readValue(entity.getContent(), new TypeReference<List<LeagueEntryDTO>>(){});

		} catch (IOException e){
			logger.error("HTTP request failed with exception", e);
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public LeagueListDTO getLeagueList(String leagueId) {
		LeagueListDTO result;

		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(apiUrl + "/lol/league/v4/leagues/" + leagueId + "?api_key=" + myKey);

			logger.debug("HTTP request: {}", request);
			HttpResponse response = client.execute(request);

			if(response.getStatusLine().getStatusCode() != 200){
				logger.error("HTTP request failed with status code: {}", response.getStatusLine().getStatusCode());
				return null;
			}

			HttpEntity entity = response.getEntity();
			result = objectMapper.readValue(entity.getContent(), LeagueListDTO.class);

		} catch (IOException e){
			logger.error("HTTP request failed with exception", e);
			e.printStackTrace();
			return null;
		}
		return result;
	}
}
