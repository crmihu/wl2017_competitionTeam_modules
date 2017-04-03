package be.wl2017.backend.umpireGame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by M999CMI on 08/03/2017.
 */
@Service
@Transactional
public class UmpireGameServiceImpl implements UmpireGameService {

@Autowired
UmpireGameRepository repository ;


    @Override
    public void save(UmpireGame umpireGame) {

        repository.save(umpireGame);

    }

}
