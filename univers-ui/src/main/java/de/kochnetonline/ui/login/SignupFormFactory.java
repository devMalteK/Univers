package de.kochnetonline.ui.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.kochnetonline.service.security.RegisterUserService;
import de.kochnetonline.ui.commons.UIComponentBuilder;

@Component
public class SignupFormFactory implements UIComponentBuilder {

	@Autowired
	private RegisterUserService registerUserService;
	
	
	private class SignupForm {
		private VerticalLayout root;
		private Panel panel;
		private TextField username;
		private PasswordField password;
		private PasswordField passwordagain;
		private Button saveButton;

		public SignupForm init() {

			root = new VerticalLayout();
			root.setMargin(true);
			root.setHeight("100%");

			panel = new Panel("SignUp");
			panel.setSizeUndefined();

			saveButton = new Button("Save");
			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

			username = new TextField("Username");
			password = new PasswordField("Password");
			passwordagain = new PasswordField("Password again");

			saveButton.addClickListener(new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					if (!passwordagain.getValue().equals(password.getValue())) {
						Notification.show("Error", "Passwords are not the same!", Type.ERROR_MESSAGE);
						return;
					}
					registerUserService.save(username.getValue(), password.getValue());
					UI.getCurrent().getPage().setLocation("/univers-web/login");
				}
			});
			return this;
		}

		public com.vaadin.ui.Component layout() {
			root.addComponent(panel);
			root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

			FormLayout signupLayout = new FormLayout();
			signupLayout.addComponent(username);
			signupLayout.addComponent(password);
			signupLayout.addComponent(passwordagain);

			signupLayout.addComponent(saveButton);
			signupLayout.setSizeUndefined();
			signupLayout.setMargin(true);

			panel.setContent(signupLayout);

			return root;

		}

	}

	@Override
	public com.vaadin.ui.Component createComponent() {
		return new SignupForm().init().layout(); 
	}

}
