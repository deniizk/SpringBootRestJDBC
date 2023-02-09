package com.garanti.SpringBootRestJDBC.service;

import com.garanti.SpringBootRestJDBC.model.Ogretmen;
import com.garanti.SpringBootRestJDBC.repo.OgretmenRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
// burası hem business hem proxy
@lombok.extern.slf4j.Slf4j(topic = "ogretmen_service")
public class OgretmenService
{
    @NonNull
    private OgretmenRepo repo;

    // private final Logger log = LoggerFactory.getLogger(this.getClass());
    // private final Logger log = org.slf4j.LoggerFactory.getLogger("ogretmen_service");

    public List<Ogretmen> getAllLike(String name)
    {
        return repo.getAllLike(name);
    }

    public List<Ogretmen> getAll()
    {
        log.debug("ogretmen get all warning");
        List<Ogretmen> res = repo.getAll();
        log.trace("ogretmen get all size = " + res.size());
        return res;
    }

    public boolean deleteById( int id)
    {
        Integer result = 0;
        try
        {
            result = repo.deleteByIdBasic(id);
        }
        catch (Exception e)
        {
            // exception yakalamak yerine spring 'e bildirilip controller advice 'larda yakalanabilir
            return false;
        }
        return result == 1;
    }

    public Ogretmen getById(int id)
    {
        return repo.getById(id);
    }

    public boolean save (Ogretmen ogretmen)
    {
        try
        {
            return repo.save(ogretmen);
        }
        catch (Exception e)
        {
            return false;
        }
    }
}