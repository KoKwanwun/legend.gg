package com.clone.legendgg.contoller.rest;

import com.clone.legendgg.domain.dto.infoSummonerResponse;
import com.clone.legendgg.domain.entity.SummonerTier;
import com.clone.legendgg.domain.entity.Summoner;
import com.clone.legendgg.exception.CustomException;
import com.clone.legendgg.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/lol/summoner")
public class SummonerRestController {
    @Value("${riot.api.key}")
    private String API_KEY;

    @GetMapping("/{summonerName}")
    public infoSummonerResponse searchSummoner(@PathVariable String summonerName) {
        // webClient 기본 설정
        WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl("https://kr.api.riotgames.com/")
                        .build();

        // api 요청 (Header에 API_KEY 넣기)
        // block으로 비동기
        Map<String, Object> infoSummoner =
                webClient
                        .get()
                        .uri(uriBuilder ->
                                uriBuilder
                                        .path("lol/summoner/v4/summoners/by-name/" + summonerName)
                                        .build())
                        .header("X-Riot-Token", API_KEY)
                        .exchangeToMono(response -> {
                            if(response.statusCode().is4xxClientError()) {
                                return Mono.empty();
                            } else {
                                return response.bodyToMono(Map.class);
                            }
                        })
                        .block();

        // 400번대 에러라면 예외처리
        if (infoSummoner == null) {
            throw new CustomException(ErrorCode.SUMMONER_NOT_FOUNDED);
        }

        String id = infoSummoner.get("id").toString();
        Summoner summoner = Summoner.builder()
                .id(id)
                .accountId(infoSummoner.get("accountId").toString())
                .puuid(infoSummoner.get("puuid").toString())
                .name(infoSummoner.get("name").toString())
                .profileIconId(Long.parseLong(infoSummoner.get("profileIconId").toString()))
                .revisionDate(Long.parseLong(infoSummoner.get("revisionDate").toString()))
                .summonerLevel(Long.parseLong(infoSummoner.get("summonerLevel").toString()))
                .build();

        List<Map<String, Object>> infoTier =
                webClient
                        .get()
                        .uri(uriBuilder ->
                                uriBuilder
                                        .path("lol/league/v4/entries/by-summoner/" + id)
                                        .build())
                        .header("X-Riot-Token", API_KEY)
                        .retrieve()
                        .bodyToMono(List.class)
                        .block();

        String soloTier = infoTier.get(0).get("tier").toString();
        soloTier += changeRank(infoTier.get(0).get("rank").toString());

        String freeTier = infoTier.get(1).get("tier").toString();
        freeTier += changeRank(infoTier.get(1).get("rank").toString());

        SummonerTier summonerTier = SummonerTier.builder()
                .id(id)
                .soloTier(soloTier)
                .soloWin(Long.parseLong(infoTier.get(0).get("wins").toString()))
                .soloLoss(Long.parseLong(infoTier.get(0).get("losses").toString()))
                .soloLeaguePoints(Long.parseLong(infoTier.get(0).get("leaguePoints").toString()))
                .freeTier(freeTier)
                .freeWin(Long.parseLong(infoTier.get(1).get("wins").toString()))
                .freeLoss(Long.parseLong(infoTier.get(1).get("losses").toString()))
                .freeLeaguePoints(Long.parseLong(infoTier.get(1).get("leaguePoints").toString()))
                .build();

        return infoSummonerResponse.builder()
                .summoner(summoner)
                .summonerTier(summonerTier)
                .build();
    }

    private String changeRank(String rank) {
        if(rank.equals("I")){
            return " 1";
        } else if(rank.equals("II")){
            return " 2";
        } else if(rank.equals("III")){
            return " 3";
        } else if(rank.equals("IV")){
            return " 4";
        } else {
            return "";
        }
    }
}
