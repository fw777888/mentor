package com.example.mentor;

import com.example.mentor.dao.Dao;
import com.example.mentor.entity.Mentor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql({"/mentor.sql"})
class MentorApplicationTests {

    private Dao<Long, Mentor> mentorDao;

    @Autowired
    public MentorApplicationTests(Dao<Long, Mentor> mentorDao) {
        this.mentorDao = mentorDao;
    }
    private final Mentor mentorOne = Mentor.builder().name("Ivan").id(1).build();

    @Test
    void save() {
        mentorDao.save(mentorOne);
    }

}
