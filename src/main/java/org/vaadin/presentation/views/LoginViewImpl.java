package org.vaadin.presentation.views;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import org.vaadin.presentation.helper.AbstractView;
import org.vaadin.presentation.helper.LanguageSelect;
import org.vaadin.presentation.login.LoginViewPresenter;
import org.vaadin.viritin.button.PrimaryButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.vaadin.cdi.CDIView;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;


@CDIView(LoginView.ID)
@ViewMenuItem(enabled = false)
public class LoginViewImpl extends AbstractView<LoginViewPresenter> implements
LoginView {
	private static final long serialVersionUID = 1L;

	public enum Language {
		GERMAN("DE","img/flags/de.png"),ENGLISH("EN","img/flags/en.png"),FRENCH("FR","img/flags/fr.png");

		private String language;
		private final String iconPath;
		private final Resource icon;

		private Language(String language,String iconPath){
			this.language = language;
			this.iconPath = iconPath;
			this.icon = new ThemeResource(iconPath);
		}

		public String getLanguage(){
			return language;
		}

		public Resource getIcon(){
			return icon;
		}

		public String getIconPath(){
			return iconPath;
		}

	}

	private final TextField username = new MTextField()
	.withInputPrompt("username");
	private final PasswordField password = new PasswordField();
	private final Button login = new PrimaryButton("Login");
	private final LanguageSelect language = new LanguageSelect("");


	@Inject
	private Instance<LoginViewPresenter> presenterInstance;

	private final Button.ClickListener loginClickListener = event -> {
		if (language.isValid() && username.isValid() && password.isValid()) {
				getPresenter().onLoginPressed(username.getValue(),
						password.getValue(), (Language) language.getValue());
		}
	};


	public LoginViewImpl() {
			setSizeFull();
			addStyleName("login-screen");
			CssLayout loginInformation = buildLoginInformation();
		
		//default selection
		language.setValue(Language.GERMAN);
		language.setIcon(FontAwesome.LANGUAGE);
		language.setRequired(true);

		username.focus();
		username.setRequired(true);
		username.setIcon(FontAwesome.USER);
		username.setInputPrompt("username");

		password.setRequired(true);
		password.setIcon(FontAwesome.KEY);
		password.setInputPrompt("password");

		login.setIcon(FontAwesome.SIGN_IN);
		login.addClickListener(loginClickListener);

		Image image = new Image(null, new ThemeResource("img/logo.png"));

		Panel loginPanel = new Panel();
		loginPanel.addStyleName("login-form");
		loginPanel.setSizeUndefined();
		loginPanel.setContent(new MVerticalLayout(image,username, password, language,login)
		.withAlign(login, Alignment.BOTTOM_RIGHT));

		setCompositionRoot(new MVerticalLayout(loginPanel,loginInformation));
	}

	@Override
	public void showInvalidLoginNotification() {
		showNotification(new Notification("Login failed",
				"Please check your username and password",
				Notification.Type.HUMANIZED_MESSAGE));
	}

	@Override
	protected LoginViewPresenter generatePresenter() {
		return presenterInstance.get();
	}

	@Override
	public String getName() {
		return "Login";
	}

	private CssLayout buildLoginInformation() {
		CssLayout loginInformation = new CssLayout();
		loginInformation.setStyleName("login-information");
		Label loginInfoText = new Label(
				"<h1>UGG Garden Designer</h1>"
						+ "<h2>Admin & User</h2>",
				ContentMode.HTML);
		loginInformation.addComponent(loginInfoText);
		return loginInformation;
	}

	private void showNotification(Notification notification) {
		// keep the notification visible a little while after moving the
		// mouse, or until clicked
		notification.setDelayMsec(1500);
		notification.show(Page.getCurrent());
	}
}
