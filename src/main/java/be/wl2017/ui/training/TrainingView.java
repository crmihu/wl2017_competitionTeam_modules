package be.wl2017.ui.training;


import be.wl2017.backend.events.EvenimenteService;
import be.wl2017.backend.predefEvent.PredefEvent;
import be.wl2017.backend.predefEvent.PredefEventService;
import be.wl2017.backend.training.Training;
import be.wl2017.backend.training.TrainingService;
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
import java.util.List;

/**
 * Created by M999CMI on 19/01/2017.
 */

@UIScope
@SpringView(name = TrainingView.VIEW_NAME)
public class TrainingView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "training";

    @Autowired
    TrainingService service;

    @Autowired
    TrainingForm trainingForm;

    @Autowired
    EventBus.UIEventBus eventBus;

    @Autowired
    EvenimenteService evenimenteService ;

    @Autowired
    PredefEventService predefEventService ;

    private MTable<Training> list = new MTable<>(Training.class)
            .withProperties("teamName",  "pitch", "vest", "start", "end")
            .withColumnHeaders("Team", "Pitch", "Vestiaire", "Start", "End")
            .setSortableProperties("Start")
            .withFullWidth();

    private Button addNew;
    private Button edit;
    private Button delete;
    private Button events;

    public TrainingView() {
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

        events = new MButton(FontAwesome.CALENDAR, new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                addEvenimente(clickEvent);
            }

        });

    }


    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);

        addComponent(
                new MVerticalLayout(
                        new MHorizontalLayout(addNew, edit, delete, events),
                        list
                ).expand(list)
        );
        listEntities();
        list.addMValueChangeListener(new MValueChangeListener<Training>() {
            @Override
            public void valueChange(MValueChangeEvent<Training> e) {
                TrainingView.this.adjustActionButtonState();
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
        events.setEnabled(hasSelection);
    }

    static final int PAGESIZE = 45;


    private void listEntities() {
        list.setRows(service.findAll());
        adjustActionButtonState();

    }

    public void add(Button.ClickEvent clickEvent) {
        edit(new Training());
    }

    public void edit(Button.ClickEvent e) {
        edit(list.getValue());
    }

    public void remove(Button.ClickEvent e) {
        service.delete(list.getValue());
        list.setValue(null);
        listEntities();
    }

    protected void edit(final Training training) {
        trainingForm.setEntity(training);
        trainingForm.openInModalPopup();
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    public void onTrainingModified(TrainingModifiedEvent event) {
        listEntities();
        trainingForm.closePopup();
    }
    private void addEvenimente(Button.ClickEvent clickEvent) {
        Training training = list.getValue() ;

        evenimenteService.deleteByEveniment("TRAINING"+Long.toString(training.getId()));


        if (training.getPredefEventName() != null) {
            List<PredefEvent> predefEvents = predefEventService.getPredefEvents(training.getPredefEventName());
            for (PredefEvent p : predefEvents)
            {
                evenimenteService.save(training, p);

            }
        }

    }

}
