package be.wl2017.backend.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by M999CMI on 22/02/2017.
 */
@Repository
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository repository ;


    @Override
    public List<Hotel> findAll() {
        return repository.findAllByOrderByNameAsc();
    }

    @Override
    public void delete(Hotel hotel) {
        repository.delete(hotel);
    }

    @Override
    public void save(Hotel hotel) {
repository.save(hotel);
    }

    @Override
    public Hotel refresh(Hotel hotel) {
        return repository.findOne(hotel.getId());
    }

    @Override
    public List<String> findAllNames() {
        return repository.findAllNames();
    }
}
