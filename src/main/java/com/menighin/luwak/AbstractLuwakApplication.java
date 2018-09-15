package com.menighin.luwak;

import com.menighin.luwak.core.interfaces.ILuwakFilter;
import com.menighin.luwak.core.interfaces.ILuwakModel;
import com.menighin.luwak.core.models.AbstractLuwakPage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@ComponentScan
@Component
public abstract class AbstractLuwakApplication {

	private HashMap<String, Class<? extends ILuwakFilter>> _pagesMap;

	public AbstractLuwakApplication() {
		_pagesMap = new HashMap<>();
	}

	protected void registerPage(Class type) {
		_pagesMap.put(type.getSimpleName(), type);
	}

	public AbstractLuwakPage<? extends ILuwakModel, ? extends ILuwakFilter> getPage(String pageName) {
		try {
			return (AbstractLuwakPage<? extends ILuwakModel, ? extends ILuwakFilter>) _pagesMap.get(pageName).newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	@PostConstruct
	protected abstract void setupApplication();

}
