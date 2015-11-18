package org.vaadin.presentation.login;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;

import static org.vaadin.presentation.views.LoginViewImpl.*;

/**
 * Class for retrieving and setting the name, roles and other DOM Elements.All methods of this class require that a
 *
 * {@link VaadinRequest} is bound to the current thread.
 *
 * @author nmitic, synto
 * @see com.vaadin.server.VaadinService#getCurrentRequest()
 */
public final class CurrentUser {
	public static void reset() {
		getCurrentRequest().getWrappedSession().removeAttribute(
				CURRENT_USER_SESSION_ATTRIBUTE_KEY);
		getCurrentRequest().getWrappedSession().removeAttribute(
				ROLES_ATTRIBUTE_KEY);
		getCurrentRequest().getWrappedSession().removeAttribute(
				LANGUAGE_ATTRIBUTE_KEY);
	}

	/**
	 * notes user roles
	 * @author nmitic
	 *
	 */
	public static enum Roles {
		GARDENMASTER("[GardenMaster]"), GARDENVISITOR("[Visitor]");

		private final String role;

		private Roles(String role){
			this.role = role;
		}

		public String getRole(){
			return role;
		}

		@Override
		public String toString() {
			return role;
		}
	}

	/**
	 * The attribute key used to store the username in the session.
	 */
	public static final String CURRENT_USER_SESSION_ATTRIBUTE_KEY = CurrentUser.class.getCanonicalName();
	public static final String ROLES_ATTRIBUTE_KEY = "roles";
	public static final String LANGUAGE_ATTRIBUTE_KEY = "language";

	private CurrentUser() {
	}

	/**
	 * Returns the name of the current user stored in the current session, or an
	 * empty string if no user name is stored.
	 *
	 * @throws IllegalStateException
	 *             if the current session cannot be accessed.
	 */
	public static String getUser() {
		String currentUser = (String) getCurrentRequest().getWrappedSession()
				.getAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY);
		if (currentUser == null) {
			return "";
		} else {
			return currentUser;
		}
	}

	/**
	 * Returns the roles of the current user stored in the current session, or an
	 * empty string if no user role is stored.
	 *
	 * @throws IllegalStateException
	 *             if the current session cannot be accessed.
	 */
	public static String getRoles() {
		String roles = (String) getCurrentRequest().getWrappedSession()
				.getAttribute(ROLES_ATTRIBUTE_KEY);
		if (roles == null) {
			return "";
		} else {
			return roles;
		}
	}

	/**
	 * Returns the language of the current user stored in the current session, or an
	 * empty string if no user language is stored.
	 *
	 * @throws IllegalStateException
	 *             if the current session cannot be accessed.
	 */
	public static Language getLanguage(){
		Language language = (Language) getCurrentRequest().getWrappedSession()
				.getAttribute(LANGUAGE_ATTRIBUTE_KEY);
		return language;
	}


	/**
	 * Sets the name,role,language and drivers of the current user and stores it in the current session.
	 * Using a {@code null} username will remove the username from the session.
	 *
	 * @throws IllegalStateException
	 *             if the current session cannot be accessed.
	 */
	public static void setUser(String currentUser, String roles, Language language) {
		if (currentUser == null) {
			getCurrentRequest().getWrappedSession().removeAttribute(
					CURRENT_USER_SESSION_ATTRIBUTE_KEY);
			getCurrentRequest().getWrappedSession().removeAttribute(
					ROLES_ATTRIBUTE_KEY);
			getCurrentRequest().getWrappedSession().removeAttribute(
					LANGUAGE_ATTRIBUTE_KEY);
		} else {
			getCurrentRequest().getWrappedSession().setAttribute(
					CURRENT_USER_SESSION_ATTRIBUTE_KEY, currentUser);
			getCurrentRequest().getWrappedSession().setAttribute(
					ROLES_ATTRIBUTE_KEY, roles);
			getCurrentRequest().getWrappedSession().setAttribute(
					LANGUAGE_ATTRIBUTE_KEY, language);
		}
	}

	public static VaadinRequest getCurrentRequest() {
		VaadinRequest request = VaadinService.getCurrentRequest();
		if (request == null) {
			throw new IllegalStateException(
					"No request bound to current thread");
		}

		return request;
	}

}
