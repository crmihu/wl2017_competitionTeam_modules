package be.wl2017.backend.training;


import be.wl2017.backend.events.Evenimente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


/**
 * Created by M999CMI on 22/02/2017.
 */
public interface TrainingRepository extends JpaRepository<Training, Long>
{

    Training findBy(long id);

    @Query("SELECT t FROM Training t order by t.start asc, t.teamName asc")
    List<Training> findAll() ;
}
