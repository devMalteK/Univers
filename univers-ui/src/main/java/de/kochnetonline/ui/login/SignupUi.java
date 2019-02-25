package de.kochnetonline.ui.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI(path=SignupUi.PATH)
@Theme("valo")
public class SignupUi extends UI {
	
	public static final String PATH = "/signup";

	@Autowired
	private SignupFormFactory signupFormFactory;
	
	
	@Override
	protected void init(VaadinRequest request) {
		setContent(signupFormFactory.createComponent());
	}
	
	

}
