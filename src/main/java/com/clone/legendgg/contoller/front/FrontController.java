package com.clone.legendgg.contoller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @GetMapping()
    public String index() {
        return "index";
    }

//    @GetMapping("/champion")
//    public String champion() {
//        return "champion";
//    }
}
