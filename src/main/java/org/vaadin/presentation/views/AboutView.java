package org.vaadin.presentation.views;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.backend.*;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import org.vaadin.cdiviewmenu.ViewMenuUI;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/*
 * A very simple view that just displays an "about text". The view also has 
 * a button to reset the demo date in the database.
 */
@CDIView("")
@ViewMenuItem(icon = FontAwesome.INFO, order = ViewMenuItem.BEGINNING)
public class AboutView extends MVerticalLayout implements View {

    @Inject
    CustomerService customerService;
    @Inject
    CommentService commentService;
    @Inject
    FieldService fieldService;
    @Inject
    ForumService forumService;
    @Inject
    GardenService gardenService;
    @Inject
    PestControlService pestControlService;
    @Inject
    PestService pestService;
    @Inject
    PlantedPlantService plantedPlantService;
    @Inject
    PlantService plantService;
    @Inject
    SeedService seedService;


    @PostConstruct
    void init() {
        add(new RichText().withMarkDownResource("/about.md"));

        int records = customerService.findAll().size();
        add(new Label("There are " + records + " records in the DB."));

        Button addCustomerDada = new Button("Fill Customer test data into DB", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                customerService.resetTestData();
                ViewMenuUI.getMenu().navigateTo(CustomerListView.class);
            }
        });
        addCustomerDada.setStyleName(ValoTheme.BUTTON_LARGE);
        addCustomerDada.addStyleName(ValoTheme.BUTTON_PRIMARY);
        add(addCustomerDada);

        Button addGardenData = new Button("Empty and Refill Garden DB with static Sample Data", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                plantService.resetTestData();
                fieldService.resetTestData();
                gardenService.resetTestData();
                pestService.resetTestData();
                pestControlService.resetTestData();
                plantService.resetTestData();
                seedService.resetTestData();
                ViewMenuUI.getMenu().navigateTo(CustomerListView.class);
            }
        });
        addGardenData.setStyleName(ValoTheme.BUTTON_LARGE);
        addGardenData.addStyleName(ValoTheme.BUTTON_PRIMARY);
        add(addGardenData);

        setMargin(new MarginInfo(false, true, true, true));
        setStyleName(ValoTheme.LAYOUT_CARD);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }
}
