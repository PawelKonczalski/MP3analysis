package pl.sda.mp3analysis.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sf;

    private HibernateUtils() {
    }

    public static Session getSession() {
        if (sf == null) {
            sf = new Configuration()
                    .configure()
                    .buildSessionFactory();
            return sf.openSession();
        }
        return sf.openSession();
    }
}