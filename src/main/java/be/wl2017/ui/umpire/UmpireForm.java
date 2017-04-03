package be.wl2017.ui.umpire;


import be.wl2017.backend.officials.umpire.Umpire;
import be.wl2017.backend.officials.umpire.UmpireService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * Created by M999CMI on 19/01/2017.
 */

@UIScope
@SpringComponent
public class UmpireForm extends AbstractForm<Umpire> {

    private static final long serialVersionUID = 1L;

    @Autowired
    EventBus.UIEventBus eventBus;

    @Autowired
    UmpireService service;

    TypedSelect<String> umpireType = new TypedSelect<>(String.class)
            .withSelectType(ComboBox.class)
            .withWidth("150px")
            .setInputPrompt("Pick a type");

    MTextField  umpireName = new MTextField().withInputPrompt("Name");
    MTextField  umpireEmail = new MTextField().withInputPrompt("Email");
    MTextField  umpirePhone = new MTextField().withInputPrompt("phone");




    UmpireForm() {
        setCaption("Umpire Management");

    }

    @PostConstruct
    void init() {
        ArrayList<String> typeList = new ArrayList<String>() ;
        typeList.add("Umpire") ;
        typeList.add("Video Umpire") ;


        umpireType
                .setOptions(typeList) ;

        // On save & cancel, publish events that other parts of the UI can listen
        setSavedHandler(new SavedHandler<Umpire>() {
            @Override
            public void onSave(Umpire umpire) {
                // persist changes
                service.save(umpire);
                // send the event for other parts of the application
                eventBus.publish(UmpireForm.this, new UmpireModifiedEvent(umpire));
            }
        });
        setResetHandler(new ResetHandler<Umpire>() {
            @Override
            public void onReset(Umpire p) {
                eventBus.publish(UmpireForm.this, new UmpireModifiedEvent(p));
            }
        });

        setSizeUndefined();
    }

    @Override
    protected Component createContent() {


        return new MVerticalLayout(
                new MFormLayout(
                umpireType, umpireName, umpireEmail, umpirePhone).withWidth(""),
                getToolbar()
        ).withWidth("");
    }

    }
