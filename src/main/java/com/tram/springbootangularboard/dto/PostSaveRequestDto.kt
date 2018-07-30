package com.tram.springbootangularboard.dto

import com.tram.springbootangularboard.domain.Post

data class PostSaveRequestDto(
        var title: String = "",
        var content: String = "",
        var author: String = ""
){
    fun toEntity(): Post {
        return Post(this.title, this.content, this.author);
    }
}