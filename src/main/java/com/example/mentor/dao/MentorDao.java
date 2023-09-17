package com.example.mentor.dao;

import com.example.mentor.entity.Mentor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.mentor.util.HibernateUtil.buildSessionFactory;

@Repository
public class MentorDao implements Dao<Long, Mentor> {
    private final static SessionFactory SESSION_FACTORY = buildSessionFactory();
    @Override
    public void save(Mentor entity) {
        try (var session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public Optional<Mentor> findBYId(Long id) {
        try (var session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            var optionalMentor = Optional.ofNullable(session.get(Mentor.class, id));
            session.getTransaction().commit();
        return optionalMentor;
        }
    }

    @Override
    public List<Mentor> findAll() {
        try (var session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            final var listMentor = session
                    .createQuery("select m from Mentor m", Mentor.class)
                    .list();
            session.getTransaction().commit();
        return listMentor;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (var session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            final var countDelete = session
                    .createQuery("delete from Mentor m where m.id = " + id, Mentor.class)
                    .executeUpdate();
            session.getTransaction().commit();
        return countDelete > 0;
        }
    }
}
