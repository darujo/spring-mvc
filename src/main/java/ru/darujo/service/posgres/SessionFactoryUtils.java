package ru.darujo.service.posgres;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Component
public class SessionFactoryUtils {
    private SessionFactory  sessionFactory;
    @PostConstruct
    private void init(){
            sessionFactory = new Configuration()
                                 .configure("hibernate.cfg.xml")
                                 .buildSessionFactory();
    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    public void shutdown(){
        sessionFactory.close();
    }
}
