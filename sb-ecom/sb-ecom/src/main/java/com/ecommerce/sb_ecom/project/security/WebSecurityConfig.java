package com.ecommerce.sb_ecom.project.security;

import com.ecommerce.sb_ecom.project.model.AppRole;
import com.ecommerce.sb_ecom.project.model.Role;
import com.ecommerce.sb_ecom.project.model.User;
import com.ecommerce.sb_ecom.project.repositories.RoleRepository;
import com.ecommerce.sb_ecom.project.repositories.UserRepository;
import com.ecommerce.sb_ecom.project.security.jwt.AuthEntryPointJwt;
import com.ecommerce.sb_ecom.project.security.jwt.AuthTokenFilter;
import com.ecommerce.sb_ecom.project.security.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Set;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;

    public WebSecurityConfig(
            UserDetailsServiceImpl userDetailsService,
            AuthEntryPointJwt unauthorizedHandler
    ) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    // ================= PASSWORD =================
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ================= AUTH PROVIDER =================
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // ================= AUTH MANAGER =================
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    // ================= JWT FILTER =================
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    // ================= SECURITY FILTER CHAIN =================
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.exceptionHandling(ex ->
                ex.authenticationEntryPoint(unauthorizedHandler)
        );

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/test/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/swagger-resources/**").permitAll()
                .requestMatchers("/webjars/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/images/**").permitAll()
                .anyRequest().authenticated()
        );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(
                authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class
        );

        http.headers(headers ->
                headers.frameOptions(frame -> frame.sameOrigin())
        );

        return http.build();
    }

    // ================= IGNORE (TUTORIAL STYLE) =================
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**"
        );
    }

    // ================= INIT DATA =================
    @Bean
    public CommandLineRunner initData(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder encoder
    ) {
        return args -> {

            Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_USER)));

            Role sellerRole = roleRepository.findByRoleName(AppRole.ROLE_SELLER)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_SELLER)));

            Role adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_ADMIN)));

            if (!userRepository.existsByUserName("user1")) {
                userRepository.save(
                        new User("user1", "user1@example.com", encoder.encode("password1"))
                );
            }

            if (!userRepository.existsByUserName("seller1")) {
                userRepository.save(
                        new User("seller1", "seller1@example.com", encoder.encode("password2"))
                );
            }

            if (!userRepository.existsByUserName("admin")) {
                userRepository.save(
                        new User("admin", "admin@example.com", encoder.encode("adminPass"))
                );
            }

            userRepository.findByUserName("user1").ifPresent(u -> {
                u.setRoles(Set.of(userRole));
                userRepository.save(u);
            });

            userRepository.findByUserName("seller1").ifPresent(s -> {
                s.setRoles(Set.of(sellerRole));
                userRepository.save(s);
            });

            userRepository.findByUserName("admin").ifPresent(a -> {
                a.setRoles(Set.of(userRole, sellerRole, adminRole));
                userRepository.save(a);
            });
        };
    }
}
