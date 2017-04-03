package be.wl2017.backend.umpireGame;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by M999CMI on 16/03/2017.
 */
@Entity
@Table(name = "umpire_game")
public class UmpireGame implements Serializable {


    private long id;
    private String vest;
    private String umpireName ;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UMPIRE_GAME_ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVest() {
        return vest;
    }

    public void setVest(String vest) {
        this.vest = vest;
    }

    public String getUmpireName() {
        return umpireName;
    }

    public void setUmpireName(String umpireName) {
        this.umpireName = umpireName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UmpireGame)) return false;

        UmpireGame umpireName = (UmpireGame) o;

        return getId() == umpireName.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public String toString() {
        return "umpireName{}";
    }
}
