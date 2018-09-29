package com.menighin.luwak

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import java.util.*


@Configuration
open class LuwakConfig {

	@Bean
	open fun localeResolver(): LocaleResolver {
		val sessionLocaleResolver = SessionLocaleResolver()
		sessionLocaleResolver.setDefaultLocale(Locale("pt"))
		return sessionLocaleResolver
	}

	@Bean
	open fun localeChangeInterceptor(): LocaleChangeInterceptor {
		val lci = LocaleChangeInterceptor()
		lci.paramName = "lang"
		return lci
	}

	@Bean
	open fun messageSource(): ResourceBundleMessageSource {
		val source = ResourceBundleMessageSource()
		source.setBasenames("i18n/messages")  // name of the resource bundle
		source.setUseCodeAsDefaultMessage(true)
		return source
	}

}