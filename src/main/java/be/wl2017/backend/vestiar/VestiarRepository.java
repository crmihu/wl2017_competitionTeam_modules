package be.wl2017.backend.vestiar;

import be.wl2017.backend.teams.Teams;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by M999CMI on 22/02/2017.
 */
public interface VestiarRepository extends JpaRepository<Vestiar, Long>
{

    @Query("SELECT v.name FROM Vestiar v ")
    List<String> findAllNames();

    @Query("SELECT v.name FROM Vestiar v where v.location = :location")
    List<String> findAllNamesByLocation(@Param("location") String location);

}
