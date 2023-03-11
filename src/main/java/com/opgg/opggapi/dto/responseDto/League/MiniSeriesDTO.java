package com.opgg.opggapi.dto.responseDto.League;

import lombok.Data;

@Data
public class MiniSeriesDTO {
	private int losses;
	private String progress;
	private int target;
	private int wins;
}
