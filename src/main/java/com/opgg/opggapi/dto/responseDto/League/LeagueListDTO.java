package com.opgg.opggapi.dto.responseDto.League;

import java.util.List;
import lombok.Data;

@Data
public class LeagueListDTO {
	private String leagueId;
	private List<LeagueItemDTO> entries;
	private String tier;
	private String name;
	private String queue;
}
