package com.garanti.SpringBootRestJDBC.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int yas;
    private String isim;

   /* public Person(int yas, String isim) {
        System.err.println("----> " + yas + " - " + isim);
        this.yas = yas;
        this.isim = isim;
    }*/
}
