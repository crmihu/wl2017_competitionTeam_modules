package be.wl2017.ui.meteo;


import be.wl2017.backend.meteo.Meteo;
import be.wl2017.backend.meteo.MeteoService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.viritin.button.ConfirmButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.MValueChangeEvent;
import org.vaadin.viritin.fields.MValueChangeListener;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by M999CMI on 19/01/2017.
 */

@UIScope
@SpringView(name = MeteoView.VIEW_NAME)
public class MeteoView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "meteo";

    @Autowired
    MeteoService service;

    @Autowired
    MeteoForm form;

    @Autowired
    EventBus.UIEventBus eventBus;

    private MTable<Meteo> list = new MTable<>(Meteo.class)
            .withProperties("date", "text")
            .withColumnHeaders("Date","Text")
            .setSortableProperties("date")
            .withFullWidth();

    private Button addNew;
    private Button edit;
    private Button delete;


    public MeteoView() {
        addNew = new MButton(FontAwesome.PLUS, new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                add(clickEvent);
            }
        });
        edit = new MButton(FontAwesome.PENCIL_SQUARE_O, new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                edit(clickEvent);
            }
        });
        delete = new ConfirmButton(FontAwesome.TRASH_O,
                "Are you sure you want to delete the entry?", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                remove(clickEvent);
            }
        });

    }


    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);



        addComponent(
                new MVerticalLayout(
                        new MHorizontalLayout(addNew, edit, delete),
                        list
                ).expand(list)
        );
        listEntities();
        list.addMValueChangeListener(new MValueChangeListener<Meteo>() {
            @Override
            public void valueChange(MValueChangeEvent<Meteo> e) {
                MeteoView.this.adjustActionButtonState();
            }
        });


        // Listen to change events emitted by OfficialsForm see onEvent method
        eventBus.subscribe(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
    protected void adjustActionButtonState() {
        boolean hasSelection = list.getValue() != null;
        edit.setEnabled(hasSelection);
        delete.setEnabled(hasSelection);

    }

    static final int PAGESIZE = 45;

    private void listEntities() {
        list.setRows(service.findAll());
        adjustActionButtonState();
    }


    public void add(Button.ClickEvent clickEvent) {

        edit(new Meteo());
    }

    public void edit(Button.ClickEvent e) {
        edit(list.getValue());
    }

    public void remove(Button.ClickEvent e) {
        service.delete(list.getValue());
        list.setValue(null);
        listEntities();
    }

    protected void edit(final Meteo meteo) {
        form.setEntity(meteo);
        form.openInModalPopup();
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    public void onMeteoModified(MeteoModifiedEvent event) {
        listEntities();
        form.closePopup();
    }

}
