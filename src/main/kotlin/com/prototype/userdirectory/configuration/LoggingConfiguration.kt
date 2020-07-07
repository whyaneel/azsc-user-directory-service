package com.prototype.userdirectory.configuration

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter

@Configuration
class LoggingConfiguration {
    @Bean
    fun requestLoggingFilter(): CommonsRequestLoggingFilter =
        CommonsRequestLoggingFilter().apply {
            setIncludeClientInfo(true)
            setIncludeQueryString(true)
            setIncludePayload(true)
        }

    @Bean
    fun commonsRequestLoggingFilter(): FilterRegistrationBean<CommonsRequestLoggingFilter> {
        val registration = FilterRegistrationBean<CommonsRequestLoggingFilter>()
        registration.setFilter(requestLoggingFilter())
        registration.addUrlPatterns("/api/*")
        return registration
    }
}