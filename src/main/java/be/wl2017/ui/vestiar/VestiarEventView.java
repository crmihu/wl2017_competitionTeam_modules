package be.wl2017.ui.vestiar;


import be.wl2017.backend.events.Evenimente;
import be.wl2017.backend.events.EvenimenteService;
import be.wl2017.ui.events.EvenimenteForm;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.button.DownloadButton;
import org.vaadin.viritin.fields.MDateField;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by M999CMI on 19/01/2017.
 */

@UIScope
@SpringView(name = VestiarEventView.VIEW_NAME)
public class VestiarEventView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "vestiarEvent";


    @Autowired
    EvenimenteService service;



    private DateField vestiarDate = new MDateField();
    private Button dailyPdfDownload ;
    private Button allPdfDownload ;
    private MTable<Evenimente> list = new MTable<>(Evenimente.class)
            .withProperties("caption", "event_location", "start")
            .withColumnHeaders("Team", "Location", "Start")
            .setSortableProperties("Start")
            .withFullWidth();

    private List<EvenimenteForm> evenimente = new ArrayList<>();

    public VestiarEventView() {

        vestiarDate.setValue(new Date());

        DateFormat df = new SimpleDateFormat("yyyyMMdd");

        dailyPdfDownload = new DownloadButton(
                out -> service.writeDailyVestiarAsPdf(out, vestiarDate.getValue()))
                .setFileName(
                        "VestiaireTeam_"+df.format(vestiarDate.getValue())+".pdf")
                .withIcon(FontAwesome.FILE_PDF_O)
                .withStyleName(ValoTheme.BUTTON_ICON_ONLY);

        allPdfDownload = new DownloadButton(
                out -> service.writeAllVestiaireAsPdf(out))
                .setFileName(
                        "VestiaireTeam.pdf")
                .withIcon(FontAwesome.FILE_PDF_O)
                .withStyleName(ValoTheme.BUTTON_ICON_ONLY);

        vestiarDate.addValueChangeListener(new DateField.ValueChangeListener(){
            @Override

            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                listEntities(vestiarDate);
            }
        });
    }

    private void listEntities(DateField transportDate) {
        list.setRows(service.findVestiaireByDate(transportDate.getValue()));
    }


    @PostConstruct
    void init() {

        setMargin(true);
        setSpacing(true);

        addComponent(
                new MVerticalLayout(
                        new MHorizontalLayout(new Label("Select the Event Date"), vestiarDate),
                        new MHorizontalLayout(dailyPdfDownload, new MLabel(" - Download the daily planning"), allPdfDownload, new MLabel(" - Download the complete planning")),
                        list
                ).expand(list)
        );


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }

}
