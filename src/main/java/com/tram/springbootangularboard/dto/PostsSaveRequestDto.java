package com.tram.springbootangularboard.dto;

import com.tram.springbootangularboard.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto implements Serializable{

    private static final long serialVersionUID = 3718292693206248753L;
    private String title;
    private String content;
    private String author;

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
