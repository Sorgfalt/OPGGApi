package com.opgg.opggapi.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueEntriesDto {
    private String leagueId;

    private String queueType;

    private String tier;

    private String rank;

    private String leaguePoints;

    private String wins;

    private String losses;

    public LeagueEntriesDto(){}
}
