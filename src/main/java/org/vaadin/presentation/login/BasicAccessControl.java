package org.vaadin.presentation.login;


import com.vaadin.cdi.access.JaasAccessControl;

import javax.enterprise.inject.Specializes;

import static org.vaadin.presentation.login.CurrentUser.*;

/**
 * Default implementation of {@link JaasAccessControl} default JAAS.
 */

@Specializes
public class BasicAccessControl extends JaasAccessControl {
	private static final long serialVersionUID = 1L;


	@Override
	public boolean isUserSignedIn() {
		return getUser().isEmpty();
	}

	@Override
	public String getPrincipalName() {
		if (getUser() != null) {
			return getUser();
		}
		return null;
	}

	@Override
	public boolean isUserInRole(String role) {
		if(getRoles() == null){
			return false;
		} else if (getRoles().contains(role)) {
			return true;
		}
		return false;
	}

}