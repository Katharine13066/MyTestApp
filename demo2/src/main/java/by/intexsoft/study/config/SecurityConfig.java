package by.intexsoft.study.config;

import by.intexsoft.study.security.JwtConfigurer;
import by.intexsoft.study.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*   @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**",
                " /v3/api-docs/**", "/library/v3/api-docs/**",
                " /v2/api-docs/**", "/library/v2/api-docs/**",
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/library-openapi/**");

       /*
        .antMatchers("/swagger-ui/**", "/library-openapi/**").permitAll()
                .antMatchers("/swagger-ui/**",
                        " /v3/api-docs/**", "/library/v3/api-docs/**",
                        " /v2/api-docs/**", "/library/v2/api-docs/**",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/library-openapi/**").permitAll()

    }
    */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/library-openapi/**").permitAll()
                .antMatchers("/swagger-ui/**",
                        " /v3/api-docs/**", "/library/v3/api-docs/**",
                        " /v2/api-docs/**", "/library/v2/api-docs/**",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/library-openapi/**").permitAll()
                .antMatchers(HttpMethod.GET,"/books", "/authors", "/feedback",
                        "/bookhistory", "/books/{id}", "/authors/{id}", "/feedback/{id}",
                        "/bookhistory/{id}", "/books/top10books", "/authors/top10authors",
                        "/feedback/getFeedbackByBookId/{id}", "/bookhistory/getBookHistoryByBookId/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/books/take_book/{bookId}/{userId}",
                        "/books/return_book/{bookId}/{userId}",
                        "/feedback").permitAll()
                .antMatchers(HttpMethod.POST, "/feedback", "/authentication").permitAll()
                .antMatchers(HttpMethod.GET, "/users", "/users/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/books/delete/{id}",  "/authors/delete/{id}",
                         "/users/{id}", "/feedback/delete/{id}",
                         "/bookhistory/delete/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/books", "/authors", "/user", "/bookhistory").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/books", "/authors", "/user", "/bookhistory").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/books", "/authors", "/user", "/bookhistory", "/feedback").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

}