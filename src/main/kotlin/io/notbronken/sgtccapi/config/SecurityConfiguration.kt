package io.notbronken.sgtccapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfiguration {


    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
//        http.addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)

        http
            .csrf { it.disable() }
            .cors { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .userDetailsService(this.userDetailsService())
            .authorizeRequests { auth ->
                auth.antMatchers("/api/**").authenticated()
                auth.antMatchers("/auth", "/auth/**").permitAll()
                auth.anyRequest().permitAll()
            }
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.NEVER)
            }

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService? {
        val user: UserDetails = User.withDefaultPasswordEncoder()
            .username("client")
            .password("password")
            .roles("USER")
            .build()
        val dianne: UserDetails = User.withDefaultPasswordEncoder()
            .username("dianne")
            .password("password")
            .roles("USER")
            .build()
        val rod: UserDetails = User.withDefaultPasswordEncoder()
            .username("rod")
            .password("password")
            .roles("USER", "ADMIN")
            .build()
        val scott: UserDetails = User.withDefaultPasswordEncoder()
            .username("scott")
            .password("password")
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user, dianne, rod, scott)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    companion object {
        const val authorization = "Authorization"
        const val bearer = "Bearer"
    }
}