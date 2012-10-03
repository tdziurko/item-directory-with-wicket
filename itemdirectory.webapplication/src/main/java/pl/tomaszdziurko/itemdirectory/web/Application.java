package pl.tomaszdziurko.itemdirectory.web;

import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.tomaszdziurko.itemdirectory.web.view.HomePage;
import pl.tomaszdziurko.itemdirectory.web.view.locations.LocationsPage;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Locale;

@Component(value = "wicketApplication")
public class Application extends WebApplication {

    private static final String DEFAULT_ENCODING = "UTF-8";
    public static final int LANGUAGE_COOKIE_AGE = 60 * 60 * 24 * 31 * 6;
    public static final String LANGUAGE_COOKIE_NAME = "LANG";

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void init() {
        super.init();

        getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext, true));

        getMarkupSettings().setDefaultMarkupEncoding(DEFAULT_ENCODING);
        getRequestCycleSettings().setResponseRequestEncoding(DEFAULT_ENCODING);

        mountBookmarkablePages();
        mountErrorLandingPages();

        if (getConfigurationType().equals(RuntimeConfigurationType.DEPLOYMENT)) {
            getMarkupSettings().setStripWicketTags(true);
            getMarkupSettings().setStripComments(true);
            getMarkupSettings().setCompressWhitespace(true);
        }

    }

    private void mountBookmarkablePages() {
        mountPage("locations", LocationsPage.class);
    }

    private void mountErrorLandingPages() {

    }

    @Override
    public RuntimeConfigurationType getConfigurationType() {
        return RuntimeConfigurationType.DEVELOPMENT;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    public static Application get() {
        return (Application) WebApplication.get();
    }

    @Override
    public Session newSession(Request request, Response response) {

        Session session = super.newSession(request, response);
        session = trySetLanguageFromCookie(session, request, response);

        return session;
    }

    private Session trySetLanguageFromCookie(Session session, Request request, Response response) {

        List<Cookie> cookies = ((WebRequest) request).getCookies();

        if (cookies == null || cookies.isEmpty()) {
            return session;
        }


        for (Cookie cookie : cookies) {
            if (LANGUAGE_COOKIE_NAME.equals(cookie.getName())) {
                session.setLocale(new Locale(cookie.getValue()));

                cookie.setMaxAge(LANGUAGE_COOKIE_AGE);
                ((WebResponse)response).addCookie(cookie);
                break;
            }
        }

        return session;
    }
}
