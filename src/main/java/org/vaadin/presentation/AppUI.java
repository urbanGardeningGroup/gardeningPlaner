package org.vaadin.presentation;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.vaadin.cdiviewmenu.ViewMenuUI;
import org.vaadin.presentation.login.*;
import org.vaadin.presentation.views.AboutView;
import org.vaadin.presentation.views.LoginView;

import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

import  static com.vaadin.ui.MenuBar.Command;
import static org.vaadin.presentation.IuiLabels.*;
import static org.vaadin.presentation.views.LoginViewImpl.*;


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
@Push(PushMode.AUTOMATIC)
public class AppUI extends ViewMenuUI {

    private IuiLabels uiLabels = new DefaultUiLablesImp(DE_CH);

    private BasicAccessControl accessControl = new BasicAccessControl();

    private final Command logoutClickListener = event -> {
        //removes all identifying information and invalidates their session too
        CurrentUser.reset();
        VaadinSession.getCurrent().close();
        Page.getCurrent().setLocation("");
    };
    private MenuBar.MenuItem settingsItem;

    /**
     * @return the currently active UI instance with correct type.
     */
    public static AppUI get() {
        return (AppUI) UI.getCurrent();
    }

    @Override
    protected void init(VaadinRequest request) {
        super.init(request);

        //sets custom converter factory
        VaadinSession.getCurrent().setConverterFactory(new PresentationConverter());
        setLanguage();

        //sets the error view
        getNavigator().setErrorView(ErrorView.class);

        //add user menu
        getMenu().addComponent(buildUserMenu());

        //check user session
        if (!isLoggedIn()) {
            ViewMenuUI.getMenu().setVisible(false);
            ViewMenuUI.getMenu().navigateTo(LoginView.ID);
        } else {
            if (getNavigator().getState().isEmpty()) {
                ViewMenuUI.getMenu().navigateTo(AboutView.class);
            }
        }
    }

    public boolean isLoggedIn() {
        if (!accessControl.isUserSignedIn()) {
            return false;
        }
        return true;
    }

    public void userLoggedIn(@Observes(notifyObserver = Reception.IF_EXISTS) UserLoggedInEvent event) {
        //set user attributes
        CurrentUser.setUser(event.getUsername(), event.getRoles(), event.getLanguage());
        setLanguage();
        Notification.show(uiLabels.getText(TextIdentifier.WELCOMEBACK) +" "+ event.getUsername());
        getMenu().navigateTo(AboutView.class);
        getMenu().setVisible(isLoggedIn());
    }

    private void setLanguage(){
        if(CurrentUser.getLanguage() == null) {
            VaadinSession.getCurrent().setLocale(DE_CH);
        }
        if(CurrentUser.getLanguage() == Language.ENGLISH) {
            uiLabels.setLocale(EN_US);
        }
        if(CurrentUser.getLanguage() == Language.FRENCH) {
            uiLabels.setLocale(FR_CH);
        }
        if(CurrentUser.getLanguage() == Language.GERMAN) {
            uiLabels.setLocale(DE_CH);
        }
    }

    private Component buildUserMenu() {
        MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        settingsItem = settings.addItem("", new ThemeResource(
                "img/profile-pic-300px.jpg"), null);
        settingsItem.addItem("Preferences", null);
        settingsItem.addSeparator();
        settingsItem.addItem("Sign Out", logoutClickListener);
        return settings;
    }

    public void updateUserName() {
        settingsItem.setText(CurrentUser.getUser());
    }

    public BasicAccessControl getAccessControl() {
        return accessControl;
    }

    public IuiLabels getUiLabels(){
        return uiLabels;
    }

}
