package be.wl2017.backend.meteo;

import java.util.Date;
import java.util.List;

/**
 * Created by calin on 01/04/2017.
 */
public interface MeteoService {
    Meteo getMeteo(Date date);
    List<Meteo> findAll() ;
    void delete(Meteo meteo);
    void save(Meteo meteo);
    Meteo refresh(Meteo value);
}
