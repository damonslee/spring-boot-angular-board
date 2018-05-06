package com.tram.springbootangularboard.domain

import org.springframework.data.repository.CrudRepository
import java.util.*

interface AccountRepository : CrudRepository<Account, Long> {
    fun findByUserId(userId: String): Optional<Account>?
    fun findBySocialId(socialId: String): Optional<Account>?
}