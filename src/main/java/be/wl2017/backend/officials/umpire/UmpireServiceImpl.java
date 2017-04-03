package be.wl2017.backend.officials.umpire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by M999CMI on 22/02/2017.
 */
@Repository
public class UmpireServiceImpl implements UmpireService {

    @Autowired
    UmpireRepository repository ;


    @Override
    public void delete(Umpire umpire) {
        repository.delete(umpire);
    }

    @Override
    public void save(Umpire umpire) {
repository.save(umpire);
    }

    @Override
    public Umpire refresh(Umpire value) {
        return repository.findBy(value.getId());
    }

    @Override
    public List<String> findAllNames() {
        return repository.findAllNames();
    }

    @Override
    public List<Umpire> findAll() {
        return repository.findAll();
    }


}
