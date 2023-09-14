package com.example.mentor.dao;

import com.example.mentor.model.Mentor;
import com.example.mentor.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.mentor.util.HibernateUtil.buildSessionFactory;

@Repository
public class MentorDao implements Dao<Long, Mentor>{
    private final static SessionFactory sessionFactory = buildSessionFactory();
    @Override
    public void save(Mentor entity) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public Optional<Mentor> findBYId(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var optionalMentor = Optional.ofNullable(session.get(Mentor.class, id));
            session.getTransaction().commit();
        return optionalMentor;
        }
    }
}
