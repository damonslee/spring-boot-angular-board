package com.tram.springbootangularboard.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostsRepositoryTest {
    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        /**
         이후 테스트 코드에 영향을 끼치지 않기 위해
         테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
         **/
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글작성테스트 () {
        Posts posts = new Posts();

        postsRepository.save(posts.builder()
                .title("제목")
                .content("내용")
                .author("규남").build());
        List<Posts> insertedPosts = postsRepository.findAll();
        Posts insertedPost = insertedPosts.get(0);
        assertEquals("제목", insertedPost.getTitle());
        assertEquals("내용", insertedPost.getContent());
        assertEquals("규남", insertedPost.getAuthor());
    }
}