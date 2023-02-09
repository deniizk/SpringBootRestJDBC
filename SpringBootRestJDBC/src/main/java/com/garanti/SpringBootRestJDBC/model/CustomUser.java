package com.garanti.SpringBootRestJDBC.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// extends yapılabilr
public class CustomUser // extends org.springframework.security.core.userdetails.User
{
    private String username = "";

    private String password = "";

    public CustomUser(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    // hibernate kullanıldığında burada olur
    // private List<com.garanti.SpringBootRestJDBC.model.Role> roles;
}