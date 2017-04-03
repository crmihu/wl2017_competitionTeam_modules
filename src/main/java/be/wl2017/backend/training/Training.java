package be.wl2017.backend.training;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by M999CMI on 20/02/2017.
 */
@Entity
public class Training implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="TRAINING_ID")
    private long id;
    private String teamName;
    private String pitch ;
    private String vest ;
    private Date start ;
    private Date end ;
    private String predefEventName ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String team) {
        this.teamName = team;
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getPredefEventName() {
        return predefEventName;
    }

    public void setPredefEventName(String predefEventName) {
        this.predefEventName = predefEventName;
    }

    public String getVest() {
        return vest;
    }

    public void setVest(String vest) {
        this.vest = vest;
    }
}
