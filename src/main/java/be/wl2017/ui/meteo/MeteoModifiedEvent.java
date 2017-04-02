package be.wl2017.ui.meteo;

import be.wl2017.backend.meteo.Meteo;

import java.io.Serializable;

/**
 * Created by M999CMI on 08/03/2017.
 */
public class MeteoModifiedEvent implements Serializable {

    private final Meteo meteo;

    public MeteoModifiedEvent(Meteo p) {
        this.meteo = p;
    }

    public Meteo getMeteo() {
        return meteo;
    }

}