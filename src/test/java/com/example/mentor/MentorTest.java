package com.example.mentor;

import com.example.mentor.dao.Dao;
import com.example.mentor.model.Mentor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("mentor.sql")
public class MentorTest {
    private final Dao<Long, Mentor> mentorDao;

    @Autowired
    public MentorTest(Dao<Long, Mentor> mentorDao) {
        this.mentorDao = mentorDao;
    }

    @Test
    void save() {
        mentorDao.findBYId(1L).ifPresent(System.out::println);
    }
    @Test
    void get() {

    }
}
