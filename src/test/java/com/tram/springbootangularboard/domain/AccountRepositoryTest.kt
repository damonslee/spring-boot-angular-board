package com.tram.springbootangularboard.domain

import com.tram.springbootangularboard.security.AccountContext
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertNotNull

@SpringBootTest
@RunWith(SpringRunner::class)
class AccountRepositoryTest {
    @Autowired
    lateinit var accountRepository: AccountRepository

    @Test
    fun createTest() {
        var account: Account = Account()
        accountRepository.save(Account())
        assertNotNull(accountRepository.findByUserId("디지몬"))
    }
}