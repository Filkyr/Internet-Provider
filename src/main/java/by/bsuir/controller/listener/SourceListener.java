package by.bsuir.controller.listener;

import by.bsuir.service.factory.ServiceFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SourceListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServiceFactory.getInstance().getSourceService().init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServiceFactory.getInstance().getSourceService().destroy();
    }
}
