package com.HarmyIndustries.Hitube.controller;

import com.HarmyIndustries.Hitube.model.Post;
import com.HarmyIndustries.Hitube.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('posts:read')")
    public String getCertainPost(@PathVariable("id") long id, Model model){
        model.addAttribute("post",postService.getPostById(id));

        return "certain";
    }

    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable("id") long id,Model model){
        Post post = postService.getPostById(id);
        model.addAttribute("post",post);
        return "edit";
    }

    @PatchMapping("/update/{id}")
    public String updatePost(@ModelAttribute("post") Post post, @PathVariable("id") long id){
        postService.updatePost(id,post);
        return "redirect:/main";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('posts:write')")
    public String deletePost(@ModelAttribute("post") Post post, @PathVariable("id") long id){
        postService.deletePost(id);
        return "redirect:/main";
    }

    @GetMapping("/{id}/link")
    public String generateLink(Model model, @PathVariable("id")long id){
        String link = "localhost:8080/posts/" + id;
        model.addAttribute("link",link);
        return "link";
    }


}
