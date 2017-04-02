package be.wl2017.backend.vestiar;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by M999CMI on 20/02/2017.
 */
@Entity
public class Vestiar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="VEST_ID")
    private long id;
    private String name;
    private String location ;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
