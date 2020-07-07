package com.prototype.userdirectory.security

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
@Configuration
class UserDirectoryAppWebSecurityConfigurerAdapter : WebSecurityConfigurerAdapter() {
    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS)
            .antMatchers("/assets/**")
    }

    // TODO Protect this API with Authentication
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .antMatcher("/api/**")
    }
}