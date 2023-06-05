package com.HarmyIndustries.Hitube.config;

import com.HarmyIndustries.Hitube.model.Permission;
import com.HarmyIndustries.Hitube.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;


    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .authorities(Role.USER.getAuthorities())
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .authorities(Role.ADMIN.getAuthorities())
                .build();

        return new InMemoryUserDetailsManager(user, admin);

    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests((requests) -> requests
                        .requestMatchers("/","/registration").permitAll()
                        .anyRequest().authenticated()
//                        .and()
//                        .requestMatchers("/user/**").hasRole("USER")
                ).formLogin((form) -> form
                                .loginPage("/login")
                                .permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
//                        {
//                    auth.requestMatchers("/").permitAll();
//                    auth.requestMatchers("/user/**").hasRole("USER");
//                    auth.requestMatchers("/admin/**").hasRole("ADMIN");
//                }

//                )
//                .httpBasic(withDefaults())
//                .build();
    }

//    @Bean
//    public UserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        return users;
//    }
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password, active from usr where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");
    }
}


