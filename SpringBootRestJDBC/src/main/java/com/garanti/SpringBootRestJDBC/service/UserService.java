package com.garanti.SpringBootRestJDBC.service;

import com.garanti.SpringBootRestJDBC.model.CustomUser;
import com.garanti.SpringBootRestJDBC.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // olmazsa bean olmaz, o zaman da çalışmaz
@AllArgsConstructor
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService
{
    private UserRepository userRepository;
    // BCryptPasswordEncoder
    private PasswordEncoder bCryptPasswordEncoder;

    public void saveUser(String username, String password)
    {
        // password burada 1234 olur
        // encode olunca asdfıhsdljkndsfşljgna gibi bişey olur
        CustomUser usr = new CustomUser(username, bCryptPasswordEncoder.encode(password));
        userRepository.save(usr);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        // db kodu burası
        // kendi user 'ımdan spring security user 'ına dönüştürüyorum
        CustomUser myUser = userRepository.getByUserName(username);
        if (myUser == null)
        {
            // hatayı spring security 'ye bildir
            // burada şifre kontrolü yok
            throw new UsernameNotFoundException("Kullanıcı adı bulunamadı");
        }
        // CustomUser sınıfı spring 'in user sınıfından extend edilebilir
        // burada kendi user 'ımı spring security user 'ına çeviriyorum
        User.UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(myUser.getUsername());
        // burası şifreli şifre
        builder.password(myUser.getPassword());
        // builder.disabled(true);
        List<GrantedAuthority> roles = userRepository.getUserRoles(myUser.getUsername());
        builder.authorities(roles);
        return builder.build();
    }
}