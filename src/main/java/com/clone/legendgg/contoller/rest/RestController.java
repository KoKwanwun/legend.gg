package com.clone.legendgg.contoller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/lol/summoner")
public class RestController {

    @GetMapping("/{summonerName}")
    public String searchSummoner(@PathVariable String summonerName) {
        return summonerName;
    }
}
