package com.nighthawk.spring_portfolio.mvc.chatboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/post")
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @GetMapping("/post/{id}")
    public Post getPost(@PathVariable String id) {
        Long postID = Long.parseLong(id);
        Optional<Post> post = postRepository.findById(postID);
        return post.get();
    }

    @PostMapping("/post/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post newPost) {
        Long postID = Long.parseLong(id);
        Optional<Post> post = postRepository.findById(postID);
        post.get().setTitle(newPost.getTitle());
        post.get().setContent(newPost.getContent());
        postRepository.save(post.get());
        return post.get();
    }

    @PostMapping("/post")
    public Post createPost(@RequestBody Post post) {
        // Get the title and content from the request body
        String title = post.getTitle();
        String content = post.getContent();
        String writer = post.getWriter();
        // Create a new Post object
        Post newPost = new Post();
        newPost.setWriter(writer);
        newPost.setTitle(title); // Set the title
        newPost.setContent(content);
        // Set other fields as needed

        // Save the new Post
        postRepository.save(newPost);

        return newPost;
    }

    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable String id) {
        Long postID = Long.parseLong(id);
        postRepository.deleteById(postID);
        return "Delete Success!";
    }
}
