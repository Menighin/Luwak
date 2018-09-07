package com.menighin.luwak;

import com.menighin.luwak.core.LuwakPage;
import com.menighin.luwak.core.interfaces.ILuwakFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@ComponentScan
@Component
public abstract class LuwakApplication {

	private HashMap<String, Class<? extends ILuwakFilter>> _pagesMap;

	public LuwakApplication() {
		_pagesMap = new HashMap<>();
	}

	protected void registerPage(Class type) {
		_pagesMap.put(type.getSimpleName(), type);
	}

	public LuwakPage<? extends ILuwakFilter> getPage(String pageName) {
		try {
			return (LuwakPage<? extends ILuwakFilter>) _pagesMap.get(pageName).newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	@PostConstruct
	protected abstract void setupApplication();

}
