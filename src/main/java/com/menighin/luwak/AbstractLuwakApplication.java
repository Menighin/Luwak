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

@ComponentScan
@Component
public abstract class AbstractLuwakApplication {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private AutowireCapableBeanFactory autowireCapableBeanFactory;

	private HashMap<String, Class<? extends ILuwakFilter>> _pagesMap;

	public AbstractLuwakApplication() {
		_pagesMap = new HashMap<>();
	}

	protected void registerPage(Class type) {
		_pagesMap.put(type.getSimpleName(), type);
	}

	public AbstractLuwakPage<? extends ILuwakModel, ? extends ILuwakFilter> getPage(String pageName) {
		try {
			Class pageClass = _pagesMap.get(pageName);
			AbstractLuwakPage page = (AbstractLuwakPage<? extends ILuwakModel, ? extends ILuwakFilter>) pageClass.newInstance();
			if (pageClass.isAnnotationPresent(Component.class)) {
				autowireCapableBeanFactory.autowireBean(page);
				autowireCapableBeanFactory.initializeBean(page, pageName);
			}
			return page;
		} catch (Exception e) {
			return null;
		}
	}

	@PostConstruct
	protected abstract void setupApplication();

}
