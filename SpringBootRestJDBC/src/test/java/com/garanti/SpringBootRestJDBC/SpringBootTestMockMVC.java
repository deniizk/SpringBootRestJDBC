package com.garanti.SpringBootRestJDBC;

// bütün uygulamayı kaldır ve en büyük testleri yaz
//@SpringBootTest
// AutoConfigureMockMvc yazmazsam aşağıdaki değişkeni inject edemez
//@AutoConfigureMockMvc // mvc uygualmam ayakta imişçenise
public class SpringBootTestMockMVC
{
//    @Autowired
//    public MockMvc mock;

//    @Test
//    public void saveOgretmen() throws Exception
//    {
//        String ogretmen = new ObjectMapper().writeValueAsString(new Ogretmen("junit", true));
//        RequestBuilder request = MockMvcRequestBuilders.post("/ogretmen/save").content(ogretmen).contentType(MediaType.APPLICATION_JSON).header("Accept-Language", "en");
//        MockHttpServletResponse response = mock.perform(request).andReturn().getResponse();
//        // response.getStatus() == 201
//        // response.getContentAsString() == "Successfully saved"
//        // javanın dafault keyword ü
//        // assert response.getContentAsString().equals("Successfully saved");
//        org.assertj.core.api.Assertions.assertThat(response.getContentAsString()).isEqualTo("Successfully saved");
//        // org.junit.jupiter.api.Assertions.assertEquals("Successfully saved", response.getContentAsString());
//        System.err.println("------> " + response.getContentAsString());
//    }
//
//    @Test
//    public void findById() throws Exception
//    {
//        // {"name":"Pelin", "ogr_NUMBER":123, "year" : 3}
//        // localhost:8080 'e gerek yok çünkü mock mvc testi
//        RequestBuilder request = MockMvcRequestBuilders.get("/ogretmen/getById/4");
//        // assertion gibi expectation yazabilirim
//        ResultMatcher numberMatcher = MockMvcResultMatchers.jsonPath("$.is_GICIK").value(true);
//        ResultMatcher nameMatcher = MockMvcResultMatchers.jsonPath("$.name").value("Çağatay");
//        ResultMatcher yearMatcher = MockMvcResultMatchers.jsonPath("$.id").value(4);
//        ResultMatcher okMatcer = MockMvcResultMatchers.status().isOk();
//        //mock.perform(request).andExpect(numberMatcher).andExpect(nameMatcher).andExpect(yearMatcher).andExpect(okMatcer);
//        mock.perform(request).andExpectAll(numberMatcher, nameMatcher, yearMatcher, okMatcer);
//    }
}