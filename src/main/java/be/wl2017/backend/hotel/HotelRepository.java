package be.wl2017.backend.hotel;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Created by M999CMI on 22/02/2017.
 */
public interface HotelRepository extends JpaRepository<Hotel, Long>
{
    Hotel findBy(long id);

    List<Hotel> findAllByOrderByNameAsc();

    @Query("SELECT hotel.name FROM Hotel hotel ORDER BY hotel.name")
    List<String> findAllNames();
}
