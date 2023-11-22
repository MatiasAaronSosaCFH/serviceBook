package com.servicebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;

@Controller
@RequestMapping("/")
public class PortalController {

    public String dashboard(){
        return "";
    }
}
