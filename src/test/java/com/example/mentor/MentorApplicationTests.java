package com.example.mentor;

import com.example.mentor.dao.MentorDao;
import com.example.mentor.model.Mentor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql({"/mentor.sql"})
class MentorApplicationTests {
    private final MentorDao mentorDao;

    @Autowired
    public MentorApplicationTests(MentorDao mentorDao) {
        this.mentorDao = mentorDao;
    }

    private final Mentor mentorOne = Mentor

    @Test
    void save() {
        mentorDao.save(mentorOne);
    }

}
