package com.example.mentor;

import com.example.mentor.dao.Dao;
import com.example.mentor.entity.Student;
import org.hibernate.PersistentObjectException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql({"/schema.sql", "/data.sql"})
@DisplayName("Тестирование StudentDao")
public class StudentTest {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(StudentTest.class);
    private final Dao<Long, Student> studentDao;
    private Student studentVasya = Student
            .builder()
            .name("Vasya")
            .lastName("Vasilev")
            .phone("8765432345")
            .build();

    @Autowired
    public StudentTest(Dao<Long, Student> studentDao) {
        this.studentDao = studentDao;
    }
    @DisplayName("Testing method save StudentDao")
    @Nested
    class SaveMethodDaoStudentTest {
        @Test
        @DisplayName("Positive testing save")
        void positiveTestingSave() {
            log.info("Test positiveTestingSave started");

            studentDao.save(studentVasya);
            final var studentVasyaOptional = studentDao
                    .findBYId(3L)
                    .orElseThrow(() -> new NoSuchElementException(
                            "Object is not saved in database"));

            log.info("object successfully saved in database");

            assertAll(
                    () -> assertEquals(studentVasyaOptional.getId(), studentVasya.getId(),
                            "objects have different fields id"),
                    () -> assertEquals(studentVasyaOptional.getName(), studentVasya.getName(),
                    "objects have different fields name"),
                    () -> assertEquals(studentVasyaOptional.getLastName(), studentVasya.getLastName(),
                            "objects have different fields last_name"),
                    () -> assertEquals(studentVasyaOptional.getPhone(), studentVasya.getPhone(),
                            "objects have different fields phone")
            );
                    log.info("positiveTestingSave successfully complete");
        }
    }

    @Test
    @DisplayName("negative testing save")
    void negativeTestingSave() {
        log.info("negative testing save started");

        studentVasya.setId(1L);
        final var exception =
                assertThrows(InvalidDataAccessApiUsageException.class,
                () -> studentDao.save(studentVasya), "Incorrect exception");

        assertInstanceOf(PersistentObjectException.class, exception.getCause(),
                "Incorrect exception");

        log.info("test negativeTestingSave successfully completed");
    }

    @Test
    @DisplayName("FindAllTest")
    void positiveFindAllTest() {
        log.info("positive find all test started");

        List<Student> students = studentDao.findAll();
        assertEquals(students.size(), 2);
        assertEquals(students.get(0).getId(), 1, "Id Anton Antonov" );
        assertEquals(students.get(1).getId(), 2, "Id Mihail Mihailov" );
        assertEquals(students.get(0).getName(), "Anton", "Antonovs name" );
        assertEquals(students.get(1).getName(), "Mihail", "Mihailovs name" );
        assertEquals(students.get(0).getLastName(), "Antonov", "Antonov is correct lastname" );
        assertEquals(students.get(1).getLastName(), "Mihailov", "Mihailov is correct lastname" );
        assertEquals(students.get(0).getPhone(), "23456765435", " phone 23456765435" );
        assertEquals(students.get(1).getPhone(), "45632765", "phone 45632765" );
    }

}
