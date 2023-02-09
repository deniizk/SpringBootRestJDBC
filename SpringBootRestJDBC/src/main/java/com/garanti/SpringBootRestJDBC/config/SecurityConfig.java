package com.garanti.SpringBootRestJDBC.config;

import com.garanti.SpringBootRestJDBC.security.JWTAuthenticationFilter;
import com.garanti.SpringBootRestJDBC.security.JWTAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        // Ahmet-USER
        // Deniz-ADMIN
        // sadece post işlemleri authenticated yapılabilir
        // http.authorizeRequests().antMatchers(HttpMethod.POST).authenticated();
        // geri kalan bütün endpoint 'ler permit durumunda, bunu üsttekinden önce yapamazsınız
        // http.authorizeRequests().anyRequest().permitAll();
        // ------------------------------------------
        // save işlemini sadece admin rolündekiler yapsın gibi zaten authenticate olmadan rol olmaz
        // önce anrequest yazarsam Can't configure antMatchers after anyRequest hatası olur
        // http.authorizeRequests().anyRequest().permitAll();
        // burası grantedauthority 'lere bakıyor
        // veritabanında role_user yazar burada user ile kontrol edersiniz
        // role isimleri standart değil abuziddin falan olabilir
        http.authorizeRequests().antMatchers("/ogretmen/**").hasRole("USER");
        // belki de burada belirtmek yerine controller 'da @Secured veya @preauthorize kullanınılabilir
        http.authorizeRequests().antMatchers(HttpMethod.DELETE).hasRole("ADMIN");
        // -----------------------------------------
        // http.authorizeRequests().antMatchers("/error").permitAll();
        // hepsini birden istisnasız kapatmak için tek satır
        // http.csrf().disable().authorizeRequests().anyRequest().authenticated();
        // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // -----------------------------------------
        http.csrf().disable();
        // stateless, postman 'da request 'leri cache 'lememesi için, çünkü burası restfull
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // sırası muhtemelen önemlidir
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.addFilterAfter(new JWTAuthorizationFilter(), JWTAuthenticationFilter.class);

        //http.csrf().disable().addFilter(new JWTAuthorizationFilter(authenticationManager())).addFilter(new JWTAuthenticationFilter(authenticationManager())).authorizeRequests().antMatchers(HttpMethod.POST).authenticated().and().authorizeRequests().anyRequest().permitAll();
    }
}