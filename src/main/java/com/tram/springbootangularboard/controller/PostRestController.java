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

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("")
    public ResponseEntity<List<Post>> read() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(postRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> readDetail(@PathVariable Long id) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당하는 게시물을 찾을 수 없습니다.")));
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody PostsSaveRequestDto postsSaveRequestDto) {
        Post savedPost = postRepository.save(postsSaveRequestDto.toEntity());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/posts/" + savedPost.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON_UTF8).headers(headers).build();
    }

}
