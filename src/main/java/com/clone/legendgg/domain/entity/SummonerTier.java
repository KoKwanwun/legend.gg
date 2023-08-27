package com.clone.legendgg.domain.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SummonerTier {
    private String id;
    private String soloTier;
    private Long soloWin;
    private Long soloLoss;
    private Long soloLeaguePoints;
    private String freeTier;
    private Long freeWin;
    private Long freeLoss;
    private Long freeLeaguePoints;
}
