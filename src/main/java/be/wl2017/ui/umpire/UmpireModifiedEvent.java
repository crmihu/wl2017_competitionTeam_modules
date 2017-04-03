package be.wl2017.ui.umpire;

import be.wl2017.backend.officials.umpire.Umpire;

import java.io.Serializable;

public class UmpireModifiedEvent implements Serializable {
    private final Umpire umpire;

    public UmpireModifiedEvent(Umpire p) {
        this.umpire = p;
    }

    public Umpire getUmpire() {
        return umpire;
    }

}
