package com.example.blog.entity;

import com.example.blog.dto.BlogRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

//import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Blog extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Blog(BlogRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();;
        this.contents = requestDto.getContents();

    }

    public String update(BlogRequestDto requestDto) {
        if (!Objects.equals(this.password, requestDto.getPassword())) {
            return "수정 성공";
        } else {
            this.title = requestDto.getTitle();
            this.contents = requestDto.getContents();
            return "수정 실패";
        }
    }
}