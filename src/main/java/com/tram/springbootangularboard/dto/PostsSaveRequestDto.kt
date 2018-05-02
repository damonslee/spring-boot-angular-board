package com.tram.springbootangularboard.dto

import com.tram.springbootangularboard.domain.Posts

data class PostsSaveRequestDto(
        var title: String = "",
        var content: String = "",
        var author: String = ""
){
    fun toEntity(): Posts {
        return Posts(this.title, this.content, this.author);
    }
}