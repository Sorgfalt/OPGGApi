package com.opgg.opggapi.dto.responseDto.Summoner;

import lombok.Data;

@Data
public class SummonerInfoDTO {
	private int profileIconId;
	private long summonerLevel;
	private String name;
	private String queueType;
	private String tier;
	private String leaguePoints;
	private String wins;
	private String losses;
}
