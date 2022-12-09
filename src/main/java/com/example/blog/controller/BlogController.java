package com.example.blog.controller;

import com.example.blog.dto.BlogRequestDto;
import com.example.blog.dto.BlogPasswordRequestDto;
import com.example.blog.entity.Blog;
import com.example.blog.service.BlogService;
import com.example.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/api/blogs")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto) {
        return blogService.createBlog(requestDto);
    }

    @GetMapping("/api/blogs")
    public List<Blog> getBlogs() {
        return blogService.getBlogs();
    }

    @GetMapping("/api/blogs/{id}")
    public Blog getBlogById(@PathVariable Long id){
        return blogService.getBlogById(id);
    }

    @PutMapping("/api/blogs/{id}")
    public String updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto){
        return blogService.updateBlog(id, requestDto);
    }

    @DeleteMapping("/api/blogs/{id}")
    public String deleteBlog(@PathVariable Long id, @RequestBody BlogPasswordRequestDto requestDto){
        if(blogService.deleteBlog(id, requestDto)){
            return "삭제 성공";
        }else{
            return "삭제 실패";
        }
    }

}
