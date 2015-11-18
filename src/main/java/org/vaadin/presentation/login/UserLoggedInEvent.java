package org.vaadin.presentation.login;

import static org.vaadin.presentation.views.LoginViewImpl.*;


public class UserLoggedInEvent {

	private final String username;
	private final Language language;
	private final String roles;
	

	public UserLoggedInEvent(String username, Language language, String roles)  {
		this.username = username;
		this.language = language;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public Language getLanguage(){
		return language;
	}

	public String getRoles() {
		return roles;
	}
	
}
