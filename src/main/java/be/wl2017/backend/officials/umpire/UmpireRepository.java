package be.wl2017.backend.officials.umpire;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by M999CMI on 22/02/2017.
 */
public interface UmpireRepository extends JpaRepository<Umpire, Long>
{
    Umpire findBy(long id);

    @Query("SELECT u.umpireName FROM Umpire u ORDER BY u.umpireType asc,  u.umpireName asc ")
    List<String> findAllNames();

    @Query("SELECT u FROM Umpire u ORDER BY u.umpireType asc,  u.umpireName asc ")
    List<Umpire> findAll();
}
