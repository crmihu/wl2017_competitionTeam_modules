package be.wl2017.backend.meteo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;



/**
 * Created by M999CMI on 22/02/2017.
 */
public interface MeteoRepository extends JpaRepository<Meteo, Long>
{
    Meteo findBy(long id);

    @Query("SELECT m FROM Meteo m where date(date) = date(:date)")
    Meteo getMeteo(@Param("date") Date date);
}
