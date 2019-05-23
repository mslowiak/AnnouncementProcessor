package api.configuration.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends BasicAuthenticationFilter {

    private String jwtSecret;

    JwtFilter(AuthenticationManager authenticationManager, String jwtSecret) {
        super(authenticationManager);
        this.jwtSecret = jwtSecret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String authorizationHeaderValue = request.getHeader("Authorization");
        UsernamePasswordAuthenticationToken authentication = getAuthentication(authorizationHeaderValue);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String tokenValue) throws ExpiredJwtException {
        JwtParser jwtParser = Jwts.parser().setSigningKey(jwtSecret.getBytes());
        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(tokenValue.replace("Bearer ", ""));

        String username = claimsJws.getBody().get("username").toString();
        log.info("Authenticate request for user: {}", username);

        return new UsernamePasswordAuthenticationToken(username, null, null);
    }
}
