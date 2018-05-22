package com.tram.springbootangularboard.domain

import com.tram.springbootangularboard.enum.UserRole
import javax.persistence.*

@Entity
@Table(name = "account")
data class Account(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,

        @Column(name = "account_username")
        val username: String? = "김규남",

        @Column(name = "account_user_id")
        val userId: String? = "kyunam",

        @Column(name = "account_password")
        var password: String? = "123123",

        @Column(name = "account_role")
        @Enumerated(value = EnumType.STRING)
        val userRole: UserRole? = UserRole.USER,

        @Column(name = "account_social_id")
        val socialId: Long? = null,

        @Column(name = "account_profile_thumb_url")
        val profileThumbUrl: String? = null)