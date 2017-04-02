package be.wl2017.backend.hotel;

import java.util.List;

/**
 * Created by calin on 20/02/2017.
 */
public interface HotelService {

    List<Hotel> findAll() ;
    void delete(Hotel match);
    void save(Hotel match);
    Hotel refresh(Hotel value);

    List<String> findAllNames();
}
