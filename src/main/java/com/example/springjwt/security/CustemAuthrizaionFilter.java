package com.example.springjwt.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springjwt.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@Slf4j @CrossOrigin(origins = "*")
public class CustemAuthrizaionFilter extends UsernamePasswordAuthenticationFilter {
 private final AuthenticationManager authenticationManager;


    public CustemAuthrizaionFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
      String username = request.getParameter("email");
        String password = request.getParameter("password");
 log.info("username is {}",username);
        log.info("password is {}",password);
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user=(User) authResult.getPrincipal() ;
        Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
        String access_token= JWT.create()
                .withSubject(user.getUsername()).withExpiresAt(new Date(System.currentTimeMillis()+10*10*60*100))
                        .withIssuer(request.getRequestURI().toString())
        .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

      /*  String refresh_token= JWT.create()
                .withSubject(user.getUsername()).withExpiresAt(new Date(System.currentTimeMillis()+30*10*60*100))
                .withIssuer(request.getRequestURI().toString())
                .sign(algorithm);*/
/*        response.addHeader("access_token",access_token);
        response.addHeader("refresh_token",refresh_token);*/
        Map<String,String>data=new HashMap<>();
        data.put("access_token",access_token);
        data.put("email",user.getUsername());

        /*  tokens.put("refresh_token",refresh_token);
     */   response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),data);



    }
}
