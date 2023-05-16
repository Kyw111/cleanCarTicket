package com.cleanCar.freeTicket.admin.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BaseController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/map/{gasStationId}")
    public String gasStationDetailMap(@PathVariable("gasStationId") Long gasStationId) {
        return "GasStationDetailMap";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
