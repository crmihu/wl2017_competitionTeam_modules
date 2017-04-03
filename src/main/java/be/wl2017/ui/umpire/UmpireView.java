package be.wl2017.ui.umpire;


import be.wl2017.backend.hotel.Hotel;
import be.wl2017.backend.hotel.HotelService;
import be.wl2017.backend.officials.umpire.Umpire;
import be.wl2017.backend.officials.umpire.UmpireService;
import be.wl2017.ui.hotel.HotelForm;
import be.wl2017.ui.hotel.HotelModifiedEvent;
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
@SpringView(name = UmpireView.VIEW_NAME)
public class UmpireView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "umpire";

    @Autowired
    UmpireService service;

    @Autowired
    UmpireForm form;

    @Autowired
    EventBus.UIEventBus eventBus;

    private MTable<Umpire> list = new MTable<>(Umpire.class)
            .withProperties("umpireType", "umpireName", "umpireEmail", "umpirePhone")
            .withColumnHeaders("Type","Name", "Email", "Phone")
            .setSortableProperties("name")
            .withFullWidth();

    private Button addNew;
    private Button edit;
    private Button delete;


    public UmpireView() {
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
        list.addMValueChangeListener(new MValueChangeListener<Umpire>() {
            @Override
            public void valueChange(MValueChangeEvent<Umpire> e) {
                UmpireView.this.adjustActionButtonState();
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

        edit(new Umpire());
    }

    public void edit(Button.ClickEvent e) {
        edit(list.getValue());
    }

    public void remove(Button.ClickEvent e) {
        service.delete(list.getValue());
        list.setValue(null);
        listEntities();
    }

    protected void edit(final Umpire umpire) {
        form.setEntity(umpire);
        form.openInModalPopup();
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    public void onUmpireModified(UmpireModifiedEvent event) {
        listEntities();
        form.closePopup();
    }

}
