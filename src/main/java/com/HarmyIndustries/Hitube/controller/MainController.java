package com.HarmyIndustries.Hitube.controller;

import com.HarmyIndustries.Hitube.model.Post;
import com.HarmyIndustries.Hitube.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String greeting(){
        return "greeting";
    }

    @GetMapping("/main")
    @PreAuthorize("hasAuthority('posts:read')")
    public String getMainPage(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "main";
    }

    @PostMapping("/addPost")
    @PreAuthorize("hasAuthority('posts:write')")
    public String addNewPost(@RequestParam String title, String tag, String description, Model model) {

        postService.createPost(title, tag, description);

        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "main";
    }

    @PostMapping("/filter")
    @PreAuthorize("hasAuthority('posts:read')")
    public String getByTag(@RequestParam String filter, Model model) {
        List<Post> posts = postService.getPostsByTag(filter);
        model.addAttribute("posts", posts);
        return "main";
    }


}
