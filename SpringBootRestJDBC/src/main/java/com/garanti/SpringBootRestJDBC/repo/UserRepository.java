package com.garanti.SpringBootRestJDBC.repo;

import com.garanti.SpringBootRestJDBC.model.CustomUser;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class UserRepository
{
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CustomUser getByUserName(String username)
    {
        String sql = "select * from BILGE.USERS where USERNAME = :USERNAME";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("USERNAME", username);
        return  namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(CustomUser.class));
    }

    public List<GrantedAuthority> getUserRoles(String username)
    {
        String sql = "select ROLE from BILGE.USER_ROLES where USERNAME = :USERNAME";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("USERNAME", username);
        // beanpropertymapper kullanıyorduk ama burada gerek yok çünkü tek sütun select yapıyorum
        List<String> liste = namedParameterJdbcTemplate.queryForList(sql, paramMap, String.class);
        // gereksiz oldu çünkü benim role sınıfım zaten grantedauthority implement ediyor
        List<GrantedAuthority> roles = new ArrayList<>();
        for (String role : liste)
        {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    public void save(CustomUser usr)
    {
    }
}
