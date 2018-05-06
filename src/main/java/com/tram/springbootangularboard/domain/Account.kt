package com.tram.springbootangularboard.domain

import com.tram.springbootangularboard.enum.UserRole
import javax.persistence.*

@Entity
@Table(name = "account")
data class Account(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private val id: Long? = null,

        @Column(name = "account_username")
        private val username: String? = null,

        @Column(name = "account_user_id")
        private val userId: String? = null,

        @Column(name = "account_password")
        private val password: String? = null,

        @Column(name = "account_role")
        @Enumerated(value = EnumType.STRING)
        private val userRole: UserRole? = null,

        @Column(name = "account_social_id")
        private val socialId: Long? = null,

        @Column(name = "account_profile_thumb_url")
        private val profileThumbUrl: String? = null)