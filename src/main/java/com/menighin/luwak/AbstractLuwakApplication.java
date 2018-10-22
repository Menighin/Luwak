package com.menighin.luwak;

import com.menighin.luwak.core.interfaces.ILuwakFilter;
import com.menighin.luwak.core.interfaces.ILuwakModel;
import com.menighin.luwak.core.models.AbstractLuwakPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Locale;

@ComponentScan
@Component
public abstract class AbstractLuwakApplication {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private AutowireCapableBeanFactory autowireCapableBeanFactory;

	/**
	 * Map keeping the registered pages of the application as (SimpleName) -> (Class)
	 */
	private HashMap<String, Class<? extends ILuwakFilter>> _pagesMap;

	public AbstractLuwakApplication() {
		_pagesMap = new HashMap<>();
	}

	/**
	 * Register the page in the Luwak Application.
	 * If the Page is a Spring Bean, the ApplicationContext will be used to get an instance
	 * Otherwise, a new instance will be created through reflection
	 * @param pageClass
	 */
	protected void registerPage(Class pageClass) {
		_pagesMap.put(pageClass.getSimpleName(), pageClass);
	}

	/**
	 * Gets an instance of the Luwak Page for the given name
	 * @param pageName the SimpleName of the Luwak Page Class
	 * @return An instance of the Luwak Page
	 */
	public AbstractLuwakPage<? extends ILuwakFilter> getPage(String pageName) {
		try {
			Class pageClass = _pagesMap.get(pageName);
			AbstractLuwakPage page = null;

			if (pageClass.isAnnotationPresent(Component.class)) {
				page = (AbstractLuwakPage<? extends ILuwakFilter>)applicationContext.getBean(pageClass);
			} else {
				page = (AbstractLuwakPage<? extends ILuwakFilter>) pageClass.newInstance();
			}

			return page;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Gets the Locale of the application for i18n
	 * @return Locale of the application
	 */
	public abstract Locale getLocale();

	/**
	 * Setup the application registering the pages
	 */
	@PostConstruct
	protected abstract void setupApplication();

}
