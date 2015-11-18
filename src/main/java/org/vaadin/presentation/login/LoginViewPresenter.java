package org.vaadin.presentation.login;



import com.vaadin.cdi.UIScoped;
import org.vaadin.presentation.helper.AbstractPresenter;
import org.vaadin.presentation.views.LoginView;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import static org.vaadin.presentation.views.LoginViewImpl.*;


@UIScoped
public class LoginViewPresenter extends AbstractPresenter<LoginView> {

	/**
	 * LIVE notes-endpoints
	 */
	public static final String ROLES_DATA = ("https://gardendesigner.ugg.com/roles");
	public static final String GROUPS_DATA = ("https://gardendesigner.ugg.com/groups");


	@Inject
	private Event<UserLoggedInEvent> loggedInEvent;

	@Override
	protected void onViewEnter() {

	}

	public void onLoginPressed(String username, String password, Language language) {
			// todo: Implement Login Actions
			getView().showInvalidLoginNotification();
	}

}
