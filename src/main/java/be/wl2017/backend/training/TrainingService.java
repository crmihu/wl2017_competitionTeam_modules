package be.wl2017.backend.training;

import java.util.List;

/**
 * Created by calin on 20/02/2017.
 */
public interface TrainingService {

    List<Training> findAll() ;
    void delete(Training match);
    void save(Training match);
    Training refresh(Training value);

}
