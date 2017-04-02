package be.wl2017.ui.vestiar;

import be.wl2017.backend.vestiar.Vestiar;

import java.io.Serializable;

/**
 * Created by M999CMI on 08/03/2017.
 */
public class VestiarModifiedEvent implements Serializable {

    private final Vestiar vestiar;

    public VestiarModifiedEvent(Vestiar p) {
        this.vestiar = p;
    }

    public Vestiar getVestiar() {
        return vestiar;
    }

}