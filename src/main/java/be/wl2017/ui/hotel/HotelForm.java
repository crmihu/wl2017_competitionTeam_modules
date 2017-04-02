package be.wl2017.ui.hotel;


import be.wl2017.backend.hotel.Hotel;
import be.wl2017.backend.hotel.HotelService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by M999CMI on 19/01/2017.
 */

@UIScope
@SpringComponent
public class HotelForm extends AbstractForm<Hotel> {

    private static final long serialVersionUID = 1L;

    @Autowired
    EventBus.UIEventBus eventBus;

    @Autowired
    HotelService service;

    MTextField  name = new MTextField().withInputPrompt("Name");
    MTextField  street = new MTextField().withInputPrompt("Street");
    MTextField  number = new MTextField().withInputPrompt("Number");
    MTextField  poBox = new MTextField().withInputPrompt("PO BOX");
    MTextField  phone = new MTextField().withInputPrompt("Phone");



    HotelForm() {
        setCaption("Hotel Management");

    }

    @PostConstruct
    void init() {


        // On save & cancel, publish events that other parts of the UI can listen
        setSavedHandler(new SavedHandler<Hotel>() {
            @Override
            public void onSave(Hotel hotel) {
                // persist changes
                service.save(hotel);
                // send the event for other parts of the application
                eventBus.publish(HotelForm.this, new HotelModifiedEvent(hotel));
            }
        });
        setResetHandler(new ResetHandler<Hotel>() {
            @Override
            public void onReset(Hotel p) {
                eventBus.publish(HotelForm.this, new HotelModifiedEvent(p));
            }
        });

        setSizeUndefined();
    }

    @Override
    protected Component createContent() {


        return new MVerticalLayout(
                new MFormLayout(
                name, street, number, poBox, phone).withWidth(""),
                getToolbar()
        ).withWidth("");
    }

    }
