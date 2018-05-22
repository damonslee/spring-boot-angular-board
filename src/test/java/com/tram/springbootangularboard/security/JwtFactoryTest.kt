package com.tram.springbootangularboard.security

import com.tram.springbootangularboard.domain.Account
import com.tram.springbootangularboard.enum.UserRole
import org.junit.Test
import org.junit.runner.RunWith

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class JwtFactoryTest {
    private val logger: org.slf4j.Logger = LoggerFactory.getLogger("logger")
    @Autowired
    lateinit var jwtFactory: JwtFactory

    @Test
    fun generateToken() {
        logger.info("hello")
        logger.info("jwt val,{}", jwtFactory.generateToken(AccountContext.fromAccountModel(Account())))
    }

//    companion object {
//        @Deployment
//        fun createDeployment(): JavaArchive {
//            return ShrinkWrap.create(JavaArchive::class.java)
//                    .addClass(JwtFactory::class.java)
//                    .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
//        }
//    }
}
