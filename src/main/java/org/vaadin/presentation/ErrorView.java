package org.vaadin.presentation;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.cdiviewmenu.ViewMenuUI;
import org.vaadin.presentation.login.BasicAccessControl;
import org.vaadin.presentation.login.CurrentUser;
import org.vaadin.presentation.views.AboutView;

import static org.vaadin.presentation.IuiLabels.*;

/**
 * Created by synto on 16.11.2015.
 */
public class ErrorView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;
    private BasicAccessControl accessControl = new BasicAccessControl();
    private IuiLabels uiLabels = AppUI.get().getUiLabels();
    private Label explanation;
    private CssLayout sign;
    private Button redirect;

    public ErrorView() {
        setMargin(true);
        setSpacing(true);

        Label header = new Label(uiLabels.getText(TextIdentifier.ERRORVIEWTITLE));

        header.addStyleName(Reindeer.LABEL_H1);
        addComponent(sign = new CssLayout());
        addComponent(header);
        addComponent(explanation = new Label());
        explanation.addStyleName(ValoTheme.LABEL_H2);
        sign.setIcon(FontAwesome.WARNING);
        addComponent(redirect = new Button("Home"));
        redirect.addClickListener(event -> {
            if(CurrentUser.getUser() == "") {
                Page.getCurrent().setLocation("");
            }
            ViewMenuUI.getMenu().navigateTo(AboutView.class);
        });
        redirect.addStyleName(ValoTheme.BUTTON_LINK);
        redirect.setIcon(FontAwesome.HOME);
        header.addStyleName("error-view");
        explanation.addStyleName("error-view");
        sign.setStyleName("error-sign");
        setComponentAlignment(redirect, Alignment.BOTTOM_CENTER);
        setComponentAlignment(sign, Alignment.TOP_CENTER);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        explanation.setValue(String.format(uiLabels.getText(TextIdentifier.ERRORVIEWINFO)));
    }

}
