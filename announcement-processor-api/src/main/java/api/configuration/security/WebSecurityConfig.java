package api.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("jwt.secret")
    private String JWT_SECRET;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/users/**").authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtFilter(authenticationManager(), JWT_SECRET), UsernamePasswordAuthenticationFilter.class);
    }
}
