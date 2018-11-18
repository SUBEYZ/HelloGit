package cn.bl.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sf = null;
	static {
		Configuration c = new Configuration().configure();
		sf = c.buildSessionFactory();
	}
	/**
	 * Note: This element neither has attached source nor attached Javadoc and hence no Javadoc could be found
	 * @return
	 */
	public static Session getCurrentSession() {
		return sf.getCurrentSession();
	}
	/**
	 * Note: This element neither has attached source nor attached Javadoc and hence no Javadoc could be found
	 * @return
	 */
	public static Session openSession() {
		return sf.openSession();
	}
}
