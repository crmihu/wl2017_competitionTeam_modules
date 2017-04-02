package be.wl2017.backend.meteo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by calin on 01/04/2017.
 */
@Entity
public class Meteo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="METEO_ID")
    private long id;
    private Date date ;

    @Lob
    @Column(name="TEXT", length=512)
    private String text;

    @Lob
    private byte[] image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

