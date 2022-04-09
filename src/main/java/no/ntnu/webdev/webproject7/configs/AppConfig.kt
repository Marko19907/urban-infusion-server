package no.ntnu.webdev.webproject7.configs

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(
    SecurityProperties::class
)
open class AppConfig(
    private val securityProperties: SecurityProperties
) {
    // TODO("Fix password encoder")
    /*
    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder(securityProperties.strength)
     */
}
