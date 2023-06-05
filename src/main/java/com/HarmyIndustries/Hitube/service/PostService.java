package com.HarmyIndustries.Hitube.service;

import com.HarmyIndustries.Hitube.model.Post;
import com.HarmyIndustries.Hitube.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void createPost(String title, String tag, String description) {
        Post post = new Post(title, tag, description);
        postRepository.save(post);
    }

    public List<Post> getPostsByTag(String tag) {

        if (tag != null && !tag.isEmpty()) {
            return postRepository.findByTag(tag);
        } else {
            return postRepository.findAll();
        }
    }

    public Post getPostById(Long id){
        return postRepository.getById(id);
    }


    public void updatePost(long id, Post updatedPost ){
        Post postToBeUpdated = getPostById(id);
        postToBeUpdated.setTitle(updatedPost.getTitle());
        postToBeUpdated.setTag(updatedPost.getTag());
        postToBeUpdated.setDescription(updatedPost.getDescription());
    }

    public void deletePost(long id){
        postRepository.deleteById(id);
    }

}

