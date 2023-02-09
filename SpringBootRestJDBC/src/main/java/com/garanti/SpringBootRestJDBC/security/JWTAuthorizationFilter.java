package com.garanti.SpringBootRestJDBC.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

// OncePerRequestFilter sayesinde security context 'ine attığımız sadece requestte geçerli oluyor
public class JWTAuthorizationFilter extends OncePerRequestFilter
{
    // dofilter yerine spring security classları(filterlarını) kullandığım için dointernalfilter
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException
    {
        // sadece post 'lar için çalışabilir bu şekilde
//        if (req.getMethod() != "POST")
//        {
//            chain.doFilter(req, res);
//        }
        // bu aşamada kullanıcı zaten doğrulandı ve token ı verildi
        String token = req.getHeader("Authorization");  //requestin içerisinde böyle bir header var mı diye bakıyoruz
        // Bearer 30948hgb57gbhg9wpuısgh==  bize böyle bir token gelecek ve biz de kontrol edeceğiz
        if (token != null)
        {
            try
            {
                // kullanıcı adı şifre kontrolü DEĞİL !!!
                // jwt geçerlilik kontrolü
                String user = JWT.require(Algorithm.HMAC512("MY_SECRET_KEY".getBytes())).build().verify(token.replace("Bearer ", "")).getSubject();
                System.err.println("------> kullanıcı adı ve rol = " + user);
                if (user != null)
                {
                    // Ahmet-USER
                    // Deniz-ADMIN
                    String username = user.split("-")[0];
                    SimpleGrantedAuthority auth = new org.springframework.security.core.authority.SimpleGrantedAuthority(user.split("-")[1]);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(auth));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    chain.doFilter(req, res);
                }
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
                res.setStatus(HttpStatus.UNAUTHORIZED.value());
                res.getWriter().write("Token exception => " + e.getMessage());
            }
        }
        else
        {
            // token yoksa olduğu gibi devam et
            chain.doFilter(req, res);
        }
    }
}