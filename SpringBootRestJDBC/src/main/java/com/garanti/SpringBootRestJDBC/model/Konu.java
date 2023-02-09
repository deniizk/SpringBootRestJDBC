package com.garanti.SpringBootRestJDBC.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Konu
{
    private Integer ID;

    // aman dikkat repoda boş gelmesin
    @NonNull
    private String NAME;

}
