package no.ntnu.webdev.webproject7.configs

import no.ntnu.webdev.webproject7.security.JWTAuthenticationFilter
import no.ntnu.webdev.webproject7.security.JWTAuthorizationFilter
import no.ntnu.webdev.webproject7.services.AppUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.access.expression.SecurityExpressionHandler
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.channel.ChannelProcessingFilter
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    @Autowired private val userDetailsService: AppUserDetailsService,
    @Autowired private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    @Autowired private val securityProperties: SecurityProperties
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .addFilterBefore(CorsConfig(), ChannelProcessingFilter::class.java)
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/products").permitAll()
            .antMatchers("/products/{\\d+}").permitAll()
            .antMatchers("/products/categories").permitAll()
            .antMatchers("/products/categories/**").permitAll()
            .antMatchers("/product-images/{\\d+}").permitAll()
            .antMatchers("/comments").permitAll()
            .antMatchers("/categories").permitAll()
            .antMatchers("/orders/users/{\\d+}").permitAll()
            .antMatchers("/user-images/{\\d+}").permitAll()
            .antMatchers(HttpMethod.POST, "/register").permitAll()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(JWTAuthenticationFilter(this.authenticationManager(), this.securityProperties))
            .addFilter(JWTAuthorizationFilter(
                this.authenticationManager(),
                this.userDetailsService,
                this.securityProperties
            ))
            .authorizeRequests()
            .expressionHandler(this.webExpressionHandler())
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(this.userDetailsService)
            .passwordEncoder(this.bCryptPasswordEncoder)
    }

    @Bean
    fun authProvider(): DaoAuthenticationProvider = DaoAuthenticationProvider().apply {
        this.setUserDetailsService(this@SecurityConfig.userDetailsService);
        this.setPasswordEncoder(this@SecurityConfig.bCryptPasswordEncoder);
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder();
    }

    @Bean
    fun roleHierarchy(): RoleHierarchyImpl? {
        val roleHierarchy = RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ADMIN > USER");
        return roleHierarchy;
    }

    private fun webExpressionHandler(): SecurityExpressionHandler<FilterInvocation?> {
        val defaultWebSecurityExpressionHandler = DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setRoleHierarchy(this.roleHierarchy());
        return defaultWebSecurityExpressionHandler;
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource = UrlBasedCorsConfigurationSource().also { cors ->
        CorsConfiguration().apply {
            this.allowedOrigins = listOf("*")
            this.allowedMethods = listOf("POST", "PUT", "DELETE", "GET", "OPTIONS", "HEAD")
            this.allowedHeaders = listOf(
                "Authorization",
                "Content-Type",
                "X-Requested-With",
                "Accept",
                "Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers"
            )
            this.exposedHeaders = listOf(
                "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials",
                "Authorization",
                "Content-Disposition"
            )
            this.maxAge = 3600
            cors.registerCorsConfiguration("/**", this)
        }
    }
}
