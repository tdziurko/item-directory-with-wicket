package pl.tomaszdziurko.itemdirectory.web.view;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import pl.tomaszdziurko.itemdirectory.service.users.UserService;
import pl.tomaszdziurko.itemdirectory.web.BasePage;

import java.util.Date;

public class HomePage extends BasePage {

    @SpringBean
    private UserService userService;

    public HomePage() {
        initGui();
    }

    private void initGui() {
        add(new Label("helloLabel", new StringResourceModel("welcomeLabel", this, null)));
        add(new Label("currentTime", new Date().toString()));

        add(new Label("numberOfUsers", userService.size() + ""));
    }

}
