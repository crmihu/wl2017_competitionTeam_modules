package be.wl2017.backend.officials.umpire;

import java.util.List;

/**
 * Created by calin on 20/02/2017.
 */
public interface UmpireService {


    void delete(Umpire umpire);
    void save(Umpire umpire);
    Umpire refresh(Umpire umpire);

    List<String> findAllNames();

    List<Umpire> findAll();
}
