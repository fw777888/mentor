package com.example.mentor.dao;

import com.example.mentor.entity.Student;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.mentor.util.HibernateUtil.buildSessionFactory;

@Repository
public class StudentDao implements Dao<Long, Student> {

    private final static SessionFactory SESSION_FACTORY = buildSessionFactory();

    @Override
    public void save(Student entity) {
        try (var session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public Optional<Student> findBYId(Long id) {
        try (var session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            var optionalStudent = Optional.ofNullable(session.get(Student.class, id));
            session.getTransaction().commit();
        return optionalStudent;
        }
    }

    @Override
    public List<Student> findAll() {
        try (var session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            final var listStudent = session
                    .createQuery("select s from Student s", Student.class)
                    .list();
            session.getTransaction().commit();
        return listStudent;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (var session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            final var countDelete = session.createQuery("delete from Student s where s.id = " + id, Student.class)
                    .executeUpdate();
            session.getTransaction().commit();
        return countDelete > 0;
        }
    }
}
