package com.tram.springbootangularboard.enum

enum class UserRole(val roleName : String)
{
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
    companion object {
        fun from(roleName: String): UserRole = UserRole.values().first { it.roleName == roleName }
    }
}