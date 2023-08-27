package com.clone.legendgg.domain.dto;

import com.clone.legendgg.domain.entity.Summoner;
import com.clone.legendgg.domain.entity.SummonerTier;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class infoSummonerResponse {
    private Summoner summoner;
    private SummonerTier summonerTier;
}
