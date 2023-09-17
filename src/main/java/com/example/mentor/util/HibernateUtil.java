package com.example.mentor.util;

import com.example.mentor.entity.Mentor;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Mentor.class);
        configuration.configure();
        return configuration.buildSessionFactory();
    }
}
