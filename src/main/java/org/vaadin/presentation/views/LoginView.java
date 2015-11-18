package org.vaadin.presentation.views;


import org.vaadin.presentation.helper.ApplicationView;
import org.vaadin.presentation.login.LoginViewPresenter;

public interface LoginView extends ApplicationView<LoginViewPresenter> {

    public static final String ID = "";

    void showInvalidLoginNotification();

}
