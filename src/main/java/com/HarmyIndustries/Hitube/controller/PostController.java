package com.HarmyIndustries.Hitube.controller;

import com.HarmyIndustries.Hitube.model.Post;
import com.HarmyIndustries.Hitube.repository.PostRepository;
import com.HarmyIndustries.Hitube.service.PostService;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('posts:read')")
    public String getCertainPost(@PathVariable("id") long id, Model model) {
        model.addAttribute("post", postService.getPostById(id));

        return "certain";
    }

    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable("id") long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "edit";
    }

    @PutMapping("/update/{id}")
    public String updatePost(@RequestParam String title, String tag, String description, @PathVariable("id") long id) {
        Post postFromDb = postRepository.getById(id);
        Post post = new Post(title, tag, description);
        BeanUtils.copyProperties(post, postFromDb,"id");
        postRepository.save(postFromDb);
        return "redirect:/main";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('posts:write')")
    public String deletePost(@ModelAttribute("post") Post post, @PathVariable("id") long id) {
        postService.deletePost(id);
        return "redirect:/main";
    }

    @GetMapping("/{id}/link")
    public String generateLink(Model model, @PathVariable("id") long id) {
        String link = "localhost:8080/posts/" + id;
        model.addAttribute("link", link);
        return "link";
    }


}
