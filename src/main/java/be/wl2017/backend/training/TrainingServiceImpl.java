package be.wl2017.backend.training;

import be.wl2017.backend.events.EvenimenteService;
import be.wl2017.backend.predefEvent.PredefEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by M999CMI on 22/02/2017.
 */
@Repository
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    TrainingRepository repository ;

    @Autowired
    EvenimenteService evenimenteService ;

    @Autowired
    PredefEventService predefEventService ;

    @Override
    public List<Training> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Training match) {
        repository.delete(match);
    }

    @Override
    public void save(Training match) {
        repository.save(match);

    }


    @Override
    public Training refresh(Training value) {
        return  repository.findBy(value.getId());
    }



}
