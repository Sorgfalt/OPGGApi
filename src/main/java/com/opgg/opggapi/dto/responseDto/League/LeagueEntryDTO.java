package com.opgg.opggapi.dto.responseDto.League;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueEntryDTO {
    private String leagueId;

    private String queueType;

    private String tier;

    private String rank;

    private String leaguePoints;

    private String wins;

    private String losses;

    public LeagueEntryDTO(){}
}
