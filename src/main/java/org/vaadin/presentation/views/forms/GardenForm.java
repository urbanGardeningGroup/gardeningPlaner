package org.vaadin.presentation.views.forms;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.backend.FieldService;
import org.vaadin.backend.GardenService;
import org.vaadin.backend.domain.Climate;
import org.vaadin.backend.domain.Garden;
import org.vaadin.backend.domain.GardenStatus;
import org.vaadin.backend.domain.GardenType;
import org.vaadin.presentation.events.GardenEvent;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.MultiSelectTable;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

/**
 * A UI component built to modify garden entities. The used superclass
 * provides binding to the entity object and e.g. Save/Cancel buttons by
 * default. In larger apps, you'll most likely have your own customized super
 * class for your forms.
 * <p>
 * Note, that the advanced bean binding technology in Vaadin is able to take
 * advantage also from Bean Validation annotations that are used also by e.g.
 * JPA implementation. Check out annotations in garden objects email field and
 * how they automatically reflect to the configuration of related fields in UI.
 * </p>
 */
@Dependent
public class GardenForm extends AbstractForm<Garden> {

    @Inject
    GardenService gardenService;
    @Inject
    FieldService fieldService;

    // Prepare some basic field components that our bound to entity property
    // by naming convetion, you can also use PropertyId annotation
    TextField gardenName = new MTextField("Name").withFullWidth();
    TextField gardenShortName = new MTextField("Short Name").withFullWidth();
    // Select to another entity, options are populated in the init method
    TypedSelect<GardenStatus> status = new TypedSelect().withCaption("Status");
    TypedSelect<GardenType> gardenType = new TypedSelect().withCaption("GardenType");
    OptionGroup climate = new OptionGroup("Climate");
    TwinColSelect selectFields = new TwinColSelect();
    /* "CDI interface" to notify decoupled components. Using traditional API to
     * other componets would probably be easier in small apps, but just
     * demonstrating here how all CDI stuff is available for Vaadin apps.
     */
    @Inject
    @GardenEvent(GardenEvent.Type.SAVE)
    javax.enterprise.event.Event<Garden> saveEvent;
    @Inject
    @GardenEvent(GardenEvent.Type.REFRESH)
    javax.enterprise.event.Event<Garden> refrehsEvent;
    @Inject
    @GardenEvent(GardenEvent.Type.DELETE)
    javax.enterprise.event.Event<Garden> deleteEvent;

    @Override
    protected Component createContent() {

        setStyleName(ValoTheme.LAYOUT_CARD);

        return new MVerticalLayout(
                new Header("Edit garden").setHeaderLevel(3),
                new MFormLayout(
                        gardenName,
                        gardenShortName,
                        status,
                        gardenType,
                        climate
                ).withFullWidth(),
                getToolbar()
        ).withStyleName(ValoTheme.LAYOUT_CARD);
    }

    @PostConstruct
    void init() {
        setEagerValidation(false);
        status.setWidthUndefined();
        status.setOptions(GardenStatus.values());
        climate.addItems((Object[]) Climate.values());
        climate.setStyleName(ValoTheme.OPTIONGROUP_SMALL);
        gardenType.setWidthUndefined();
        gardenType.setOptions(GardenType.values());
        setSavedHandler(new SavedHandler<Garden>() {

            @Override
            public void onSave(Garden entity) {
                try {
                    // make EJB call to save the entity
                    gardenService.saveOrPersist(entity);
                    // fire save event to let other UI components know about
                    // the change
                    saveEvent.fire(entity);
                } catch (EJBException e) {
                    /*
                     * The garden object uses optimitic locking with the
                     * version field. Notify user the editing didn't succeed.
                     */
                    Notification.show("The garden was concurrently edited "
                                    + "by someone else. Your changes were discarded.",
                            Notification.Type.ERROR_MESSAGE);
                    refrehsEvent.fire(entity);
                }
            }
        });
        setResetHandler(new ResetHandler<Garden>() {

            @Override
            public void onReset(Garden entity) {
                refrehsEvent.fire(entity);
            }
        });
        setDeleteHandler(new DeleteHandler<Garden>() {
            @Override
            public void onDelete(Garden entity) {
                gardenService.deleteEntity(getEntity());
                deleteEvent.fire(getEntity());
            }
        });
    }

    @Override
    protected void adjustResetButtonState() {
        // always enabled in this form
        getResetButton().setEnabled(true);
        getDeleteButton().setEnabled(getEntity() != null && getEntity().isPersisted());
    }
}
