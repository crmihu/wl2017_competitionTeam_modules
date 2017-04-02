package be.wl2017.backend.vestiar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by M999CMI on 08/03/2017.
 */
@Repository
public class VestiarServiceImpl implements VestiarService {

    @Autowired
    VestiarRepository repository ;


    @Override
    public void delete(Vestiar value) {
        repository.delete(value);
    }

    @Override
    public void save(Vestiar vestiar) {
repository.save(vestiar);
    }

    @Override
    public List<Vestiar> findAll() {
        return repository.findAll();
    }

    @Override
    public Vestiar refresh(Vestiar value) {
        return repository.findOne(value.getId());
    }

    @Override
    public List<String> findAllNames() {
        return repository.findAllNames();
    }

    @Override
    public List<String> findAllNamesByLocation(String location) {
        if (location != null)
        {
            return repository.findAllNamesByLocation(location);
        }else
        {
            return repository.findAllNames();
        }
    }
}
