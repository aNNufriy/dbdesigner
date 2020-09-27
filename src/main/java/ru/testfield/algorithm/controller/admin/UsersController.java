package ru.testfield.algorithm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.web.repository.UserRepository;
import ru.testfield.web.model.cms.User;
import ru.testfield.web.model.cms.Notification;
import ru.testfield.web.service.StorageService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by J.Bgood on 12/6/17.
 */
@Controller("adminUsersController")
@RequestMapping("admin/users")
public class UsersController extends AbstractController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StorageService storageService;

    @RequestMapping("")
    public String users(Model model){
        model.addAttribute("title","Users");
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("description","users list");
        return "admin/pages/users/list";
    }

    @RequestMapping(value = "/add", method= RequestMethod.GET)
    public String userAdd(Model model){
        model.addAttribute("title","Add user");
        return "admin/pages/users/edit";
    }

    @RequestMapping(value = "/edit/{id}", method= RequestMethod.GET)
    public String userEdit(@PathVariable("id") UUID id, Model model, RedirectAttributes attr){
        User user = userRepository.findOne(id);
        model.addAttribute("user",user);
        model.addAttribute("title","Edit user");
        return "admin/pages/users/edit";
    }

    @RequestMapping(value = "/edit", method= RequestMethod.GET)
    public String userEdit(){
        return "redirect:";
    }

    @ModelAttribute
    private void processFlashAttributes(RedirectAttributes attr, Model model){
        if(attr.getFlashAttributes().get("notifications")!=null){
            model.addAttribute("notifications",attr.getFlashAttributes().get("notifications"));
        }
    }

    @RequestMapping(value = "/edit", method= RequestMethod.POST)
    public String userEditPost(@ModelAttribute User user, @RequestParam(value = "avatar_image", required = false) MultipartFile file,
                                 RedirectAttributes attr, HttpServletRequest request){

        String password = user.getPassword();
        if(password!=null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if(file!=null && !file.isEmpty()) {
            user.setAvatar(storageService.store(file));
        }else{
            if(user.getId()!=null) {
                user.setAvatar(userRepository.findOne(user.getId()).getAvatar());
            }
        }

        userRepository.save(user);
        attr.addFlashAttribute("notifications",
                Arrays.asList(new Notification(Notification.NotificationType.SUCCESS,"Changes saved"))
        );
        return "redirect:edit/"+user.getId().toString();
    }
}
