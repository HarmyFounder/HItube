package com.HarmyIndustries.Hitube.controller;

import com.HarmyIndustries.Hitube.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/user/{id}")
    public String getCertainPost(@PathVariable("id") long id, Model model){
        model.addAttribute("post",postService.getPostById(id));
        return "certain";
    }

}
