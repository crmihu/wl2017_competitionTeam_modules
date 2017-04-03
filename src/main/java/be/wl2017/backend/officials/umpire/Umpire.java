package be.wl2017.backend.officials.umpire;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by M999CMI on 20/02/2017.
 */
@Entity
public class Umpire implements Serializable {

    private static final long serialVersionUID = 1L;


    private long id;


    private String umpireType;


    private String umpireName;


    private String umpireEmail;


    private String umpirePhone;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUmpireType() {
        return umpireType;
    }

    public void setUmpireType(String umpireType) {
        this.umpireType = umpireType;
    }

    public String getUmpireName() {
        return umpireName;
    }

    public void setUmpireName(String umpireName) {
        this.umpireName = umpireName;
    }

    public String getUmpireEmail() {
        return umpireEmail;
    }

    public void setUmpireEmail(String umpireEmail) {
        this.umpireEmail = umpireEmail;
    }

    public String getUmpirePhone() {
        return umpirePhone;
    }

    public void setUmpirePhone(String umpirePhone) {
        this.umpirePhone = umpirePhone;
    }
}
