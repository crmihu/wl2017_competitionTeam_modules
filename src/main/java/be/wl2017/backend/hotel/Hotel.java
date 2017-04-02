package be.wl2017.backend.hotel;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by M999CMI on 20/02/2017.
 */
@Entity
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="HOTEL_ID")
    private long id;
    private String name;
    private String street ;
    private Integer number ;
    private Integer poBox ;
    private String phone ;


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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPoBox() {
        return poBox;
    }

    public void setPoBox(Integer poBox) {
        this.poBox = poBox;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
