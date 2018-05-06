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
        val username: String? = null,

        @Column(name = "account_user_id")
        val userId: String? = null,

        @Column(name = "account_password")
        val password: String? = null,

        @Column(name = "account_role")
        @Enumerated(value = EnumType.STRING)
        val userRole: UserRole? = null,

        @Column(name = "account_social_id")
        val socialId: Long? = null,

        @Column(name = "account_profile_thumb_url")
        val profileThumbUrl: String? = null)