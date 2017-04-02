package be.wl2017.ui.vestiar;


import be.wl2017.backend.vestiar.Vestiar;
import be.wl2017.backend.vestiar.VestiarService;
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
public class VestiarForm extends AbstractForm<Vestiar> {

    private static final long serialVersionUID = 1L;

    @Autowired
    EventBus.UIEventBus eventBus;

    @Autowired
    VestiarService service;

    MTextField  name = new MTextField().withInputPrompt("Name");
    MTextField  location = new MTextField().withInputPrompt("Location");





    VestiarForm() {
        setCaption("Vestiar Management");
      }

    @PostConstruct
    void init() {


        // On save & cancel, publish events that other parts of the UI can listen
        setSavedHandler(new SavedHandler<Vestiar>() {
            @Override
            public void onSave(Vestiar vestiar) {
                // persist changes
                service.save(vestiar);
                // send the event for other parts of the application
                eventBus.publish(VestiarForm.this, new VestiarModifiedEvent(vestiar));
            }
        });
        setResetHandler(new ResetHandler<Vestiar>() {
            @Override
            public void onReset(Vestiar p) {
                eventBus.publish(VestiarForm.this, new VestiarModifiedEvent(p));
            }
        });

        setSizeUndefined();
    }

    @Override
    protected Component createContent() {



        return new MVerticalLayout(
                new MFormLayout(
                        name,location


                ).withWidth(""),
                getToolbar()
        ).withWidth("");
    }

    }
