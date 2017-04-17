package com.ksulima;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by Krzysztof Sulima on 15.04.2017.
 */


@Controller
public class ViewController {

    @RequestMapping("/start")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Public User");
        return "index";
    }


}