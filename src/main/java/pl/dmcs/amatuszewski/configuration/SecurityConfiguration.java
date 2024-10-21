package pl.dmcs.amatuszewski.configuration;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Resource(name = "myAppUserDetailsService")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/hello*", "/register*","/addAppUser*","/test/createUser*", "/activation/activate*").permitAll()
                        .requestMatchers("/home*").authenticated()
                        .requestMatchers("/appUsers*").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/visits**").hasAnyRole("ADMIN", "USER", "DOCTOR")
                        .requestMatchers("/visits/add*", "/visits/pay**","/visit_types*", "/visit_types/add**","/visit_types/edit/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/appUserRole*", "/assignRole/**", "/assignRole*", "/test/createUser*","prescriptions/delete/**").hasRole("ADMIN")
                        .requestMatchers("prescriptions/add**","prescriptions/delete/**", "prescriptions/edit/**").hasRole("DOCTOR")
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/login*").anonymous()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("login")
                        .passwordParameter("password")
                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/hello")
                        .permitAll()
                )
                .csrf().and()
                .httpBasic();

        return http.build();
    }
}
