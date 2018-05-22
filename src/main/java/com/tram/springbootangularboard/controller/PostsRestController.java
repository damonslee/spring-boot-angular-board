package com.tram.springbootangularboard.controller;

import com.tram.springbootangularboard.domain.AccountRepository;
import com.tram.springbootangularboard.domain.Posts;
import com.tram.springbootangularboard.domain.PostsRepository;
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
public class PostsRestController {
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("")
    public ResponseEntity<List<Posts>> read() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(postsRepository.findAll(), responseHeaders, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody PostsSaveRequestDto postsSaveRequestDto) {
        postsRepository.save(postsSaveRequestDto.toEntity());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
