package com.opgg.opggapi.dto.responseDto.Summoner;

import lombok.Data;

@Data
public class SummonerInfoDTO {
	private int profileIconId; //프로필 사진 번호
	private long summonerLevel; //소환사 레벨
	private String name; //소환사 닉네임
	private String queueType; //개인/2인 랭크(RANKED_SOLO_5x5) 또는 자유 랭크 구분(RANKED_FLEX_SR
	private String tier; //소환사의 랭크 티어
	private String leaguePoints; //소환사의 랭크 점수
	private String wins; //소환사의 랭크 게임 이긴 홧수
	private String losses; //소환사의 랭크 게임 진 횟수₩
}
