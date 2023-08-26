package com.clone.legendgg.contoller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FrontController {

    @GetMapping()
    public String index() {
        return "index";
    }

    @GetMapping("/{summonerName}")
    public String searchSummoner(@PathVariable String summonerName) {
        return "infoSummoner";
    }

}
