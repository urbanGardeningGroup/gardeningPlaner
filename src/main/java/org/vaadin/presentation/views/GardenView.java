package org.vaadin.presentation.views;

import com.vaadin.cdi.CDIView;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import org.vaadin.backend.GardenService;
import org.vaadin.backend.domain.Garden;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import org.vaadin.presentation.AppUI;
import org.vaadin.presentation.ScreenSize;
import org.vaadin.presentation.events.GardenEvent;
import org.vaadin.presentation.events.GardenEvent.Type;
import org.vaadin.presentation.views.forms.GardenForm;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.MValueChangeEvent;
import org.vaadin.viritin.fields.MValueChangeListener;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * A view that lists Gardens in a Table and lets user to choose one for
 * editing. There is also RIA features like on the fly filtering.
 */
@CDIView("garden")
@ViewMenuItem(icon = FontAwesome.HEART_O, order = 1)
public class GardenView extends MVerticalLayout implements View {

    @Inject
    GardenForm gardenEditor;
    // Introduce and configure some UI components used on this view
    MTable<Garden> gardenTable = new MTable(Garden.class).withFullWidth().
            withFullHeight();
    MHorizontalLayout mainContent = new MHorizontalLayout(gardenTable).
            withFullWidth().withMargin(false);
    TextField filter = new TextField();
    Header header = new Header("Gardens").setHeaderLevel(2);
    Button addButton = new MButton(FontAwesome.EDIT,
            new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    addGarden();
                }
            });
    @Inject
    private GardenService service;

    @PostConstruct
    public void init() {

        /*
         * Add value change listener to table that opens the selected Gardens into
         * an editor.
         */
        gardenTable.addMValueChangeListener(new MValueChangeListener<Garden>() {

            @Override
            public void valueChange(MValueChangeEvent<Garden> event) {
                editGarden(event.getValue());
            }
        });

        /*
         * Configure the filter input and hook to text change events to
         * repopulate the table based on given filter. Text change
         * events are sent to the server when e.g. user holds a tiny pause
         * while typing or hits enter.
         * */
        filter.setInputPrompt("Filter Gardens...");
        filter.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent textChangeEvent) {
                listGardens(textChangeEvent.getText());
            }
        });


        /* "Responsive Web Design" can be done with plain Java as well. Here we
         * e.g. do selective layouting and configure visible columns in
         * table based on available width */
        layout();
        adjustTableColumns();
        /* If you wish the UI to adapt on window resize/page orientation
         * change, hook to BrowserWindowResizeEvent */
        UI.getCurrent().setResizeLazy(true);
        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            Page.BrowserWindowResizeEvent browserWindowResizeEvent) {
                        adjustTableColumns();
                        layout();
                    }
                });

        listGardens();
    }

    /**
     * Do the application layout that is optimized for the screen size.
     * <p>
     * Like in Java world in general, Vaadin developers can modularize their
     * helpers easily and re-use existing code. E.g. In this method we are using
     * extended versions of Vaadins basic layout that has "fluent API" and this
     * way we get bit more readable code. Check out vaadin.com/directory for a
     * huge amount of helper libraries and custom components. They might be
     * valuable for your productivity.
     * </p>
     */
    private void layout() {
        removeAllComponents();
        if (ScreenSize.getScreenSize() == ScreenSize.LARGE) {
            addComponents(
                    new MHorizontalLayout(header, filter, addButton)
                            .expand(header)
                            .alignAll(Alignment.MIDDLE_LEFT),
                    mainContent
            );

            filter.setSizeUndefined();
        } else {
            addComponents(
                    header,
                    new MHorizontalLayout(filter, addButton)
                            .expand(filter)
                            .alignAll(Alignment.MIDDLE_LEFT),
                    mainContent
            );
        }
        setMargin(new MarginInfo(false, true, true, true));
        expand(mainContent);
    }

    /**
     * Similarly to layouts, we can adapt component configurations based on the
     * client details. Here we configure visible columns so that a sane amount
     * of data is displayed for various devices.
     */
    private void adjustTableColumns() {
        if (ScreenSize.getScreenSize() == ScreenSize.LARGE) {
            gardenTable.setVisibleColumns("gardenName", "gardenShortName", "gardenType",
                    "climate");
            gardenTable.setColumnHeaders("Garden Name", "Short name", "Garden Type",
                    "Climate");
        } else {
            // Only show one (generated) column with combined first + last name
            /*
            Only needed if Custom Fields (computed on behalf of other fields, are needed for display)
            if (fieldTable.getColumnGenerator("Display") == null) {
                fieldTable.addGeneratedColumn("name",
                        new Table.ColumnGenerator() {
                            @Override
                            public Object generateCell(Table table, Object o,
                                    Object o2) {
                                Customer c = (Customer) o;
                                return c.getFirstName() + " " + c.getLastName();
                            }
                        });
            }*/
            if (ScreenSize.getScreenSize() == ScreenSize.MEDIUM) {
                gardenTable.setVisibleColumns("gardenShortName", "gardenType", "climate");
                gardenTable.setColumnHeaders("Short Name", "Garden Type", "Climate");
            } else {
                gardenTable.setVisibleColumns("gardenShortName", "gardenType");
                gardenTable.setColumnHeaders("Short Name", "Garden Type");
            }
        }
    }

    /* ******* */
    // Controller methods.
    //
    // In a big project, consider using separate controller/presenter
    // for improved testability. MVP is a popular pattern for large
    // Vaadin applications.
    private void listGardens() {
        // Here we just fetch data straight from the EJB.
        //
        // If you expect a huge amount of data, do proper paging,
        // or use lazy loading Vaadin Container like LazyQueryContainer
        // See: https://vaadin.com/directory#addon/lazy-query-container:vaadin
        gardenTable.setBeans(new ArrayList<>(service.findAll()));
    }

    private void listGardens(String filterString) {
        gardenTable.setBeans(new ArrayList<>(service.findByName(filterString)));
    }

    void editGarden(Garden garden) {
        if (garden != null) {
            openEditor(garden);
        } else {
            closeEditor();
        }
    }

    void addGarden() {
        openEditor(new Garden());
    }

    private void openEditor(Garden garden) {
        gardenEditor.setEntity(garden);
        // display next to table on desktop class screens
        if (ScreenSize.getScreenSize() == ScreenSize.LARGE) {
            mainContent.addComponent(gardenEditor);
            gardenEditor.focusFirst();
        } else {
            // Replace this view with the editor in smaller devices
            AppUI.get().getContentLayout().
                    replaceComponent(this, gardenEditor);
        }
    }

    private void closeEditor() {
        // As we display the editor differently in different devices,
        // close properly in each modes
        if (gardenEditor.getParent() == mainContent) {
            mainContent.removeComponent(gardenEditor);
        } else {
            AppUI.get().getContentLayout().
                    replaceComponent(gardenEditor, this);
        }
    }

    /* These methods gets called by the CDI event system, which is also
     * available for Vaadin UIs when using Vaadin CDI add-on. In this
     * example events are arised from GardenForm. The CDI event system
     * is a great mechanism to decouple components.
     */
    void saveGarden(@Observes @GardenEvent(Type.SAVE) Garden garden) {
        listGardens();
        closeEditor();
    }

    void resetGarden(@Observes @GardenEvent(Type.REFRESH) Garden garden) {
        listGardens();
        closeEditor();
    }

    void deleteGarden(@Observes @GardenEvent(Type.DELETE) Garden garden) {
        closeEditor();
        listGardens();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}
