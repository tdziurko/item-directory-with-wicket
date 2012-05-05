package pl.tomaszdziurko.itemdirectory.web.view;


import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.protocol.http.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.tomaszdziurko.itemdirectory.web.Application;

import javax.servlet.http.Cookie;
import java.util.Locale;


/**
 * User: Tomek Dziurko (tdziurko@gmail.com)
 */

public class LanguagePanel extends Panel {

    Logger log = LoggerFactory.getLogger(LanguagePanel.class);

    public LanguagePanel(String id) {
        super(id);

        Form<Void> languageForm = new Form<Void>("languageForm");

        languageForm.add(createLocaleChangingButton("polishButton", "PL"));
        languageForm.add(createLocaleChangingButton("englishButton", "GB"));

        add(languageForm);
    }

    private Button createLocaleChangingButton(final String buttonId, final String localeString) {
        return new Button(buttonId) {
            @Override
            public void onSubmit() {
                changeUserLocaleTo(localeString);
                setResponsePage(this.getPage().getClass());
            }
        };
    }

    private void changeUserLocaleTo(String localeString) {
        getSession().setLocale(new Locale(localeString));

        Cookie languageCookie = new Cookie(Application.LANGUAGE_COOKIE_NAME, localeString);
        languageCookie.setMaxAge(Application.LANGUAGE_COOKIE_AGE);
        ((WebResponse)getResponse()).addCookie(languageCookie);
    }
}
