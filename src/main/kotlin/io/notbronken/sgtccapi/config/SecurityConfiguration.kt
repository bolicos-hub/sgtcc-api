package io.notbronken.sgtccapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono

@Configuration
class SecurityConfiguration {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        http
            .csrf { it.disable() }
            .cors { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }

            .authorizeExchange { auth ->
                auth.pathMatchers("/auth/**").permitAll()
                auth.pathMatchers("/api/**")
                    .authenticated()
                    .and()
                    .authenticationManager { manager ->
                        manager.isAuthenticated
                        manager.credentials
                        return@authenticationManager Mono.just(manager)
                    }
                auth.anyExchange().permitAll()
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
}