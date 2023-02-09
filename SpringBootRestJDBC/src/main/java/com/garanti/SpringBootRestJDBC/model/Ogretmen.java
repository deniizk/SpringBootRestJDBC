package com.garanti.SpringBootRestJDBC.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Ogretmen
{
    private Integer ID;

    @NonNull
    private String NAME;

    @NonNull
    private boolean IS_GICIK;

    // testlerde equals ile bakabilmek için
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ogretmen ogretmen = (Ogretmen) o;
        return IS_GICIK == ogretmen.IS_GICIK && Objects.equals(ID, ogretmen.ID) && NAME.equals(ogretmen.NAME);
    }
}