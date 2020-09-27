package ru.testfield.algorithm.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.testfield.algorithm.model.bfap.NodeViewModel;
import ru.testfield.algorithm.repository.NodeRepository;
import ru.testfield.algorithm.service.NodeService;
import ru.testfield.web.repository.cms.PageRepository;
import ru.testfield.web.exception.EntityNotFoundException;
import ru.testfield.web.model.cms.Page;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by J.Bgood on 7/31/17.
 */
@Controller
public class IndexController {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private NodeService nodeService;

    @RequestMapping("/storage")
    public String redirectStorage() {
        return "redirect:/storage/ps/";
    }

    UUID PS_ID = UUID.fromString("c46dcf8a-7679-43d0-baa1-f87f606be11c");

    @RequestMapping({"","front","front/"})
    public String redirectToFront(Model model){
        model.addAttribute("nodes",NodeViewModel.mapNodes(nodeRepository.findBySemNodeType_Id(PS_ID)));
        model.addAttribute("title","DB Designer Demo App");
        return "contentTable";
    }

    @RequestMapping("/login")
    public String login(Map<String, Object> model){
        return "login";
    }

    @RequestMapping("page/{id}")
    public ModelAndView showPage(@PathVariable String id){
        ModelAndView model = new ModelAndView("page");
        try {
            Page page = pageRepository.findOne(UUID.fromString(id));
            if(page==null){
                throw new EntityNotFoundException("unable to find page");
            }else {
                model.addObject("page", page);
                model.addObject("message", id);
                return model;
            }
        }catch(NumberFormatException ex){
            throw new EntityNotFoundException("unable to find page");
        }
    }

    @RequestMapping(path = "getAvatar/{avatarName:.*}", produces = MediaType.IMAGE_JPEG_VALUE)
    public  @ResponseBody byte[] getAvatar(@PathVariable String avatarName, HttpServletResponse response) throws IOException{
        response.setHeader("Content-disposition", "filename="+ "huyak.jpg");
        InputStream in = new FileInputStream("/tmp/tomcat.6496182738201243322.8080/storage/v2.jpg");
        return IOUtils.toByteArray(in);
    }

}
