package org.vaadin.presentation;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.UI;
import org.vaadin.cdiviewmenu.ViewMenuUI;


/**
 * UI class and its init method  is the "main method" for Vaadin apps.
 * But as we are using Vaadin CDI, Navigator and Views, we'll just
 * extend the helper class ViewMenuUI that provides us a top level layout,
 * automatically generated top level navigation and Vaadin Navigator usage.
 * <p>
 * We also configure the theme, host page title and the widgetset used
 * by the application.
 * </p>
 * <p>
 * The real meat of this example is in CustomerView and CustomerForm classes.
 * </p>
 */
@CDIUI("")
@Theme("valo")
@Title("Garden Designer")
@Widgetset("AppWidgetset")
//@Push(PushMode.AUTOMATIC)
public class AppUI extends ViewMenuUI {

    /**
     * @return the currently active UI instance with correct type.
     */
    public static AppUI get() {
        return (AppUI) UI.getCurrent();
    }

}
