package ru.mirea.Pegov.pkmn.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.mirea.Pegov.pkmn.security.jwt.JwtService;

import java.io.IOException;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("Authorization");

        if (!(Objects.isNull(jwtToken))) {

            for (Cookie cookie : request.getCookies()) {
                jwtToken = new String(Base64.getDecoder().decode(cookie.getValue()));
            }

            if (Objects.isNull(jwtToken)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        if (jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.split("Bearer ")[1];
        }

        DecodedJWT decodedJWT = jwtService.verify(jwtToken);
        if (Objects.isNull(decodedJWT)) {
            filterChain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        decodedJWT.getSubject(),
                        null,
                        decodedJWT.getClaim("roles").asList(SimpleGrantedAuthority.class)
                )
        );

        filterChain.doFilter(request, response);
    }
}
