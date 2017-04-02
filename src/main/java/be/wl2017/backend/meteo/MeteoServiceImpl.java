package be.wl2017.backend.meteo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by M999CMI on 22/02/2017.
 */
@Repository
public class MeteoServiceImpl implements MeteoService {

    @Autowired
    MeteoRepository repository ;

    @Override
    public Meteo getMeteo(Date date) {
        return repository.getMeteo(date);
    }

    @Override
    public List<Meteo> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Meteo meteo) {
repository.delete(meteo);
    }

    @Override
    public void save(Meteo meteo) {
repository.save(meteo);
    }

    @Override
    public Meteo refresh(Meteo value) {
        return repository.findOne(value.getId());
    }
}
