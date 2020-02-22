package com.appsdeveloperblog.app.ws;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

import com.appsdeveloperblog.app.ws.security.AppProperties;

public class SpringApplicationContext implements ApplicationContextAware {
	private static ApplicationContext CONTEXT;

    @Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		CONTEXT = context;
	}

	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}

}