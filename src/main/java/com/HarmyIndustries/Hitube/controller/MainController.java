package com.HarmyIndustries.Hitube.controller;

import com.HarmyIndustries.Hitube.entity.Post;
import com.HarmyIndustries.Hitube.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String greeting(){
        return "greeting";
    }

    @GetMapping("/user/main")
    public String getMainPage(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "main";
    }

    @PostMapping("/admin/addPost")
    public String addNewPost(@RequestParam String title, String tag, String description, Model model) {

        postService.createPost(title, tag, description);

        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "main";
    }

    @PostMapping("/user/filter")
    public String getByTag(@RequestParam String filter, Model model) {
        List<Post> posts = postService.getPostsByTag(filter);
        model.addAttribute("posts", posts);
        return "main";
    }


}
