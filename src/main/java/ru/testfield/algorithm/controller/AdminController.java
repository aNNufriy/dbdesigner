package ru.testfield.algorithm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by J.Bgood on 7/31/17.
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @RequestMapping("")
    public String index(HttpServletRequest request){
        return "redirect:/admin/data/node";
    }
}