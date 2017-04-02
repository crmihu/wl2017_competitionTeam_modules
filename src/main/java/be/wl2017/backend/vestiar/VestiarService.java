package be.wl2017.backend.vestiar;

import java.util.List;

/**
 * Created by M999CMI on 08/03/2017.
 */
public interface VestiarService {


    void delete(Vestiar value);

    void save(Vestiar teams);

    List<Vestiar> findAll();

    Vestiar refresh(Vestiar value);

    List<String> findAllNames();
    List<String> findAllNamesByLocation(String location);

}
