package com.tram.springbootangularboard.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    public void 게시글작성테스트 () {
        Post post = new Post();

        postRepository.save(post.builder()
                .title("제목")
                .content("내용")
                .author("규남").build());
        List<Post> insertedPosts = postRepository.findAll();
        Post insertedPost = insertedPosts.get(0);
        assertEquals("제목", insertedPost.getTitle());
        assertEquals("내용", insertedPost.getContent());
        assertEquals("규남", insertedPost.getAuthor());
    }
}