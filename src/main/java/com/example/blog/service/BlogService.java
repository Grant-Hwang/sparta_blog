package com.example.blog.service;

import com.example.blog.dto.BlogRequestDto;
import com.example.blog.dto.BlogPasswordRequestDto;
import com.example.blog.entity.Blog;
import com.example.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    @Transactional
    public Blog createBlog(BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);
        blogRepository.save(blog);
        return blog;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Blog> getBlogs() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Blog getBlogById(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("존재하지 않는 게시글입니다.")
        );
        return blog;
    }

    @Transactional
    public String updateBlog(Long id, BlogRequestDto requestDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("존재하지 않는 게시글입니다.")
        );
        return blog.update(requestDto);
    }

    public Boolean deleteBlog(Long id, BlogPasswordRequestDto requestDto){
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("존재하지 않는 게시글입니다.")
        );
        if(Objects.equals(blog.getPassword(), requestDto.getPassword())){
            blogRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
