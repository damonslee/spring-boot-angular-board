package com.tram.springbootangularboard.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue
    @Column(name="post_id")
    private Long id;

    @Column(name="title", length = 500, nullable = false)
    private String title;


    @Column(name="content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name="author")
    private String author;

    @Builder
    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
