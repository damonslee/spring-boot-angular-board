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
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(postRepository.findAll(), responseHeaders, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody PostsSaveRequestDto postsSaveRequestDto) {
        postRepository.save(postsSaveRequestDto.toEntity());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
