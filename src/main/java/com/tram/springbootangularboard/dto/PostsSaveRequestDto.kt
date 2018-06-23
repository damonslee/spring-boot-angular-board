package com.tram.springbootangularboard.dto

import com.tram.springbootangularboard.domain.Post

data class PostsSaveRequestDto(
        var title: String = "",
        var content: String = "",
        var author: String = ""
){
    fun toEntity(): Post {
        return Post(this.title, this.content, this.author);
    }
}