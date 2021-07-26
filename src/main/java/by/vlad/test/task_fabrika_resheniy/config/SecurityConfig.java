package by.vlad.test.task_fabrika_resheniy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/polls").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/polls").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/polls").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/polls").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/polls/{pollId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/polls/{pollId}").hasAnyRole("ADMIN", "USER")

                .antMatchers(HttpMethod.POST, "/polls/{pollId}/questions").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/polls/{pollId}/questions").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/polls/{pollId}/questions/{questionId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/polls/{pollId}/questions/{questionId}").hasRole("USER")

                .antMatchers(HttpMethod.POST, "/polls/{pollId}/questions/{questionId}/{username}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/polls/{pollId}/questions/{questionId}/{username}").hasRole("USER")

                .and().formLogin().permitAll();
    }
}
