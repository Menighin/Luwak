package com.menighin.luwak;

import com.menighin.luwak.core.LuwakPage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@ComponentScan
@Component
public abstract class LuwakApplication {

	private HashMap<String, Class> _pagesMap;

	public LuwakApplication() {
		_pagesMap = new HashMap<>();
	}

	protected<T extends LuwakPage> void registerPage(Class<T> type) {
		_pagesMap.put(type.getSimpleName(), type);
	}

	public LuwakPage getPage(String pageName) {
		try {
			return (LuwakPage) _pagesMap.get(pageName).newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	@PostConstruct
	protected abstract void setupApplication();

}
