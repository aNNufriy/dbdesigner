package ru.testfield.algorithm.controller.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public class AbstractController {

    @ModelAttribute
    private void processFlashAttributes(RedirectAttributes attr, Model modelForView, HttpServletRequest request){
        if(attr.getFlashAttributes().get("notifications")!=null){
            modelForView.addAttribute("notifications",attr.getFlashAttributes().get("notifications"));
        }

    }

}
