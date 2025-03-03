package util;

import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import pojo.*;

public class HibernateUtil {
	private static final SessionFactory sessionFactory= buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration conf = new Configuration();
			conf.configure("hibernate.cfg.xml");
			conf.addAnnotatedClass(Departamento.class);
			conf.addAnnotatedClass(Empleado.class);
			
			ServiceRegistry sr= new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
			return conf.buildSessionFactory(sr);
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}
}
