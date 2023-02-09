package com.garanti.SpringBootRestJDBC.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

// bu şekilde sadece dev profilinde çalışır
//@Profile(value = "dev")
@RestController
@RequiredArgsConstructor
public class BeansController
{
    @Value(value = "${rest.level}")
    public Integer level;

    @NonNull
    private ApplicationContext applicationContext;

    @GetMapping(path = "beans")
    public void setApplicationContext()
    {
        // localhost:9090/beans
        // context 'e ihtiyaç yok
        // localhost:9090/beans
        System.err.println("level = " + level);
        String[] names = applicationContext.getBeanDefinitionNames();
        Arrays.sort(names);
        System.err.println("----------------------------------------------------");
        System.err.println("---- " + names.length + " ----");
        for (String name : names) {
            System.err.println(name + " ---> " + applicationContext.getBean(name).getClass().getName());
        }
        System.err.println("----------------------------------------------------");
    }
}
