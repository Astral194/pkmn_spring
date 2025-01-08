package ru.mirea.Pegov.pkmn.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("Authorization");


        if ((Objects.isNull(jwtToken)) || !jwtToken.startsWith("Bearer ")) {
            if (!Objects.isNull(request.getCookies())) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals("jwt"))
                        jwtToken = new String(Base64.getDecoder().decode(cookie.getValue()));
                    System.out.println(jwtToken);
                }
            }

            if (Objects.isNull(jwtToken)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        if (jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.split("Bearer ")[1];
            log.info("JWT token: {}", jwtToken);
        }

        DecodedJWT decodedJWT = jwtService.verify(jwtToken);

        if (Objects.isNull(decodedJWT)) {
            filterChain.doFilter(request, response);
            return;
        }

        log.info("Информация токена: {}", List.of(new SimpleGrantedAuthority(decodedJWT.getClaim("authority").asString())));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        decodedJWT.getSubject(),
                        null,
                        List.of(new SimpleGrantedAuthority(decodedJWT.getClaim("authority").asString()))
                )
        );

        filterChain.doFilter(request, response);
    }
}
