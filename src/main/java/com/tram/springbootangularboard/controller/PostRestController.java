package com.tram.springbootangularboard.controller;

import com.tram.springbootangularboard.domain.Post;
import com.tram.springbootangularboard.domain.PostRepository;
import com.tram.springbootangularboard.dto.PostsSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("")
    public ResponseEntity<List<Post>> read() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(postRepository.findAll());
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody PostsSaveRequestDto postsSaveRequestDto) {
        postRepository.save(postsSaveRequestDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON_UTF8).body("게시글이 작성되었습니다.");
    }

}
