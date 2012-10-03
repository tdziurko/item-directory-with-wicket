package pl.tomaszdziurko.itemdirectory.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pl.tomaszdziurko.itemdirectory.web.view.LanguagePanel;
import pl.tomaszdziurko.itemdirectory.web.view.RightSidebarPanel;
import pl.tomaszdziurko.itemdirectory.web.view.locations.LocationsPage;

public abstract class BasePage extends WebPage {

	public BasePage() {
		super();
		initGui();
	}

	public BasePage(PageParameters parameters) {
		super(parameters);
		initGui();
	}
	
	private void initGui() {
        addHeaderLinks();
		addTopMenuLinks();

        add(new LanguagePanel("languagePanel"));
		add(new FeedbackPanel("feedbackPanel"));
        add(new RightSidebarPanel("sidebarPanel"));
	}

    private void addHeaderLinks() {
		add(new BookmarkablePageLink<BasePage>("headerHomePageLink", Application.get().getHomePage()));
		add(new BookmarkablePageLink<BasePage>("headerHomePageLink2", Application.get().getHomePage()));
	}

	private void addTopMenuLinks() {
		add(new BookmarkablePageLink<BasePage>("homePageLink", Application.get().getHomePage()));
		add(new BookmarkablePageLink<BasePage>("locationsPageLink", LocationsPage.class));
	}
	
}
