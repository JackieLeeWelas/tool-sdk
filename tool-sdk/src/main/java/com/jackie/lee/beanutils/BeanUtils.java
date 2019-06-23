package com.jackie.lee.beanutils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //获取bean
    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) {
        return applicationContext.getBean(beanName, requiredType);
    }

    /**
     * 获取spring加载的所有的bean
     * @return
     */
    public static String[] getAllAutowiredName() {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        return definitionNames;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.applicationContext = applicationContext;
    }
}
