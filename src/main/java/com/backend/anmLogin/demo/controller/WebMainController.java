package com.backend.anmLogin.demo.controller;


import com.backend.anmLogin.demo.entity.SharedText;
import com.backend.anmLogin.demo.repository.SharedTextRepository;
import com.backend.anmLogin.demo.repository.UserRepository;
import com.backend.anmLogin.demo.service.UserDetailsServiceImpl;
import com.backend.anmLogin.demo.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class WebMainController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    SharedTextRepository sharedTextRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccess";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/sharedMem", method = RequestMethod.GET)
    public String sharedMem(Model model, Principal principal)
    {
        List<SharedText> sharedTexts = new LinkedList<>();
        sharedTextRepository.findAll().forEach(sharedTexts::add);

        model.addAttribute("sharedTexts", sharedTexts);
        model.addAttribute("principalName", principal.getName());
        return "sharedMem";
    }


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }

    @RequestMapping(value = "/sharedMem/del", method = RequestMethod.GET)
    public ResponseEntity<String> deleteSharedText(@RequestParam(name = "textId") long textId, Principal principal)
    {
        if(principal == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        var text = sharedTextRepository.findById(textId);
        if(text.isPresent())
        {
            if(!Objects.equals(text.get().getOwner().getName(), principal.getName()))
            {
                return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
            }

            sharedTextRepository.delete(text.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
