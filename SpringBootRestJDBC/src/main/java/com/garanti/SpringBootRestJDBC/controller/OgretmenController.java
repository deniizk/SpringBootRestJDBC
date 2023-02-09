package com.garanti.SpringBootRestJDBC.controller;

import com.garanti.SpringBootRestJDBC.model.Ogretmen;
import com.garanti.SpringBootRestJDBC.repo.OgretmenRepo;
import com.garanti.SpringBootRestJDBC.service.OgretmenService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(path = "ogretmen")
// sadece OgretmenRepo classının hatalarını yakalamak için
@RestControllerAdvice(basePackageClasses = OgretmenRepo.class)
@io.swagger.v3.oas.annotations.tags.Tag(description = "Oğretmen endpointleri", name = "ogretmen")
public class OgretmenController
{
    // localhost:9090/FirstSpringWeb/ogretmen

    private MessageSource messageSource;

//    @ExceptionHandler(value = BadSqlGrammarException.class)
//    public void badSqlGrammerExceptionHandler(BadSqlGrammarException e)
//    {
//        // server.error.include-message = always
//        System.err.println("Bad sql yakalandı -> " + e.getMessage());
//    }

    @ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
    //@ResponseBody
    //@ResponseStatus(code = HttpStatus.IM_USED, reason = "invalid jdbc usage")
    public ResponseEntity<String> InvalidDataAccessApiUsageExceptionHandler(InvalidDataAccessApiUsageException e)
    {
        System.err.println(e.getMessage());
        // isterseniz responseentity döndürebilirsiniz
        return ResponseEntity.status(HttpStatus.IM_USED).body("yazılımcı kodu yanlış yazmış");
        // veya aşağıdaki gibi döndürülebilir
        // return "yazılımcı kodu yanlış yazmış";
    }

   /* public OgretmenController(){
        //new Person(1,"fg").toString(); lombok ile oluşturduk
        System.err.println("----------------> new yapılıyor");
    }

    @GetMapping(path = "hello")
    public String helloSpring()
    {
        // new OgretmenRepo().helloSpring();
        // localhost:9090/FirstSpringWeb/ogretmen/hello
        return "Hello Spring";
    }*/

    // bean olarak ayağa kaldırılmazsa ise bir anlamı yok
    //@Autowired
    private OgretmenService service;

    public OgretmenController(OgretmenService service, ResourceBundleMessageSource messageSource)
    {
        // this.repo = new OgretmenRepo(); // yazmak yerine dışardan yani app context ten geliyor
        // @Autowire yerine constructor injection
        this.service = service;
        this.messageSource = messageSource;
    }

    @GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ogretmen>> getAll()
    {
        // localhost:9090/ogretmen/getAll
        List<Ogretmen> res = service.getAll();
        if (res == null || res.size() == 0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping(path = "findAllByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ogretmen>> getByIdQueryParam(@RequestParam(value = "name", required = true) String name) {
        // localhost:9090/FirstSpringWeb/ogretmen/findAllByName?name=a
        return ResponseEntity.ok(this.service.getAllLike(name));
    }

    @GetMapping(path = "getByIdHeader", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ogretmen> getByIdHeader(@RequestHeader(name = "id") Integer id) {
        // localhost:9090/FirstSpringWeb/ogretmen/getById?id=1
        Ogretmen res = service.getById(id);
        if (res != null) {
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(path = "getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ogretmen> getByIdQueryParam(@RequestParam(value = "id", required = true) Integer id) {
        // localhost:9090/FirstSpringWeb/ogretmen/getById?id=1
        Ogretmen res = service.getById(id);
        if (res != null) {
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Bulunursa 200 bulunamazsa 204", summary = "ID ile getir")
    public ResponseEntity<Ogretmen> getByIdPathParam(@PathVariable(value = "id") Integer id)
    {
        // localhost:9090/ogretmen/getById/1
        Ogretmen res = service.getById(id);
        if (res != null)
        {
            return ResponseEntity.ok(res);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    // @Secured(value = "ROLE_ADMIN")
    public ResponseEntity<String> save(@RequestBody Ogretmen ogretmen, Locale locale)
    {
        // localhost:9090/ogretmen/save
        // Accept-Language header 'ını otomatik olarak locale değişkenine set eder
        // {"name":"RestTest", "is_GICIK": true}
        // System.err.println(locale.getDisplayLanguage());
        if (service.save(ogretmen))
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(messageSource.getMessage("ogretmen.save.success", null, locale));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("ogretmen.save.fail", null, locale));
        }
    }

    @DeleteMapping(path = "deleteById/{id}")
    @Hidden
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Integer id) {
        // localhost:9090/FirstSpringWeb/ogretmen/deleteById/1
        if (service.deleteById(id)) {
            return ResponseEntity.ok("Başarı ile silindi");
        } else {
            return ResponseEntity.internalServerError().body("Başarı ile silinemedi");
        }
    }

    @DeleteMapping(path = "deleteByIdHeader")
    public ResponseEntity<String> deleteByIdHeader(@RequestHeader(value = "id") Integer id) {
        // localhost:9090/FirstSpringWeb/ogretmen/deleteById/1
        if (service.deleteById(id)) {
            return ResponseEntity.ok("Başarı ile silindi");
        } else {
            return ResponseEntity.internalServerError().body("Başarı ile silinemedi");
        }
    }


}
