package com.database.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private CityService service;


    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Configurations> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Configurations config = new Configurations();
        model.addAttribute("city", config);
        return "new_city";
    }

}
