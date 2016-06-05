package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
	private static final SessionFactory sessionFactory;

	static
	{
		try
		{
			System.setProperty("org.jboss.logging.provider", "slf4j");
			Configuration cfg = new Configuration().configure();
			StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties());
			StandardServiceRegistry sr = srb.build();
			sessionFactory = cfg.buildSessionFactory(sr);
		} catch (Throwable ex)
		{
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public static void closeSessionFactory()
	{
		sessionFactory.close();
	}
}
