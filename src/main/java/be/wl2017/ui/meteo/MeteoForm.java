package be.wl2017.ui.meteo;


import be.wl2017.backend.meteo.Meteo;
import be.wl2017.backend.meteo.MeteoService;
import com.vaadin.data.Property;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.easyuploads.UploadField;
import org.vaadin.spring.events.EventBus;
import org.vaadin.viritin.MSize;
import org.vaadin.viritin.fields.MDateField;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;



/**
 * Created by M999CMI on 19/01/2017.
 */

@UIScope
@SpringComponent
public class MeteoForm extends AbstractForm<Meteo> {

    private static final long serialVersionUID = 1L;

    @Autowired
    EventBus.UIEventBus eventBus;

    @Autowired
    MeteoService service;

    private DateField date = new MDateField();

    MTextArea text = new MTextArea().withSize(MSize.size("250px", "250px"));
    private UploadField field = new UploadField();

    private byte[] image;

    MeteoForm() {
        setCaption("Hotel Management");

    }

    @PostConstruct
    void init() {
        text.setWordwrap(true);
        field.setFieldType(UploadField.FieldType.BYTE_ARRAY);

        field.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                image = (byte[]) field.getValue();

            }
        });

        // On save & cancel, publish events that other parts of the UI can listen
        setSavedHandler(new SavedHandler<Meteo>() {
            @Override
            public void onSave(Meteo meteo) {
                // persist changes
               // byte[] imgBytesAsBase64 = Base64.encodeBase64(image);
               // String imgDataAsBase64 = new String(imgBytesAsBase64);

                meteo.setImage(image);
                service.save(meteo);
                // send the event for other parts of the application
                eventBus.publish(MeteoForm.this, new MeteoModifiedEvent(meteo));
            }
        });
        setResetHandler(new ResetHandler<Meteo>() {
            @Override
            public void onReset(Meteo p) {
                eventBus.publish(MeteoForm.this, new MeteoModifiedEvent(p));
            }
        });

        setSizeUndefined();
    }

    @Override
    protected Component createContent() {


        return new MVerticalLayout(
                new MFormLayout(
                date, text, field).withWidth(""),
                getToolbar()
        ).withWidth("");
    }

    }
