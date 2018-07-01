package com.tram.springbootangularboard.config.front;

import com.tram.springbootangularboard.common.WebpackWatchExecutor;
import com.tram.springbootangularboard.config.properties.PackageJsonPathProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * angular watch를 시작과 동시에 실행시키기 위한 설정
 * local profile에서만 동작한다. dev, prod 환경에서는 동작하지 않는다.
 */
@Configuration
@Profile("local")
@EnableConfigurationProperties(PackageJsonPathProperties.class)
public class WebpackWatchConfig {
    @Autowired
    private PackageJsonPathProperties packageJsonPathProperties;

    //package.json 경로 property가 존재하면 빈 생성
    @Bean
    @ConditionalOnProperty(prefix = "npm", name = "path")
    public WebpackWatchExecutor webpackWatchExecutor() {
        return new WebpackWatchExecutor(packageJsonPathProperties.getPath());
    }
}