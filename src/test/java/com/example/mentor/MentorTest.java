package com.example.mentor;

import com.example.mentor.dao.Dao;
import com.example.mentor.entity.Mentor;
import org.hibernate.PersistentObjectException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Sql({"/schema.sql", "/data.sql" })
@DisplayName("Тестирование MentorDao")

public class MentorTest {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MentorTest.class);
    private final Dao<Long, Mentor> mentorDao;
    private Mentor mentorJohn = Mentor
            .builder()
            .name("John")
            .lastName("Johnson")
            .phone("12004449839")
            .build();


    @Autowired
    public MentorTest(Dao<Long, Mentor> mentorDao) {
        this.mentorDao = mentorDao;
    }
    @DisplayName("Testing method save MentorDao")
    @Nested
    class SaveMethodDaoMentorTest {
        @Test
        @DisplayName("Позитивное тестирование save")
        void positiveTestingSave() {
            log.info("Тест positiveTestingSave начал выполнение");

            mentorDao.save(mentorJohn);
            final var mentorJohnOptional = mentorDao
                    .findBYId(3L)
                    .orElseThrow(() -> new NoSuchElementException(
                            "Объект не сохранился в базу данных"));

            log.info("Обьект успешно сохранен в базу данных");

            assertAll(
                    () -> assertEquals(mentorJohnOptional.getId(), mentorJohn.getId(),
                            "У объектов разное поле id"),
                    () -> assertEquals(mentorJohnOptional.getName(), mentorJohn.getName(),
                            "У объектов разное поле name"),
                    () -> assertEquals(mentorJohnOptional.getLastName(), mentorJohn.getLastName(),
                            "У объектов разное поле lastName"),
                    () -> assertEquals(mentorJohnOptional.getPhone(), mentorJohn.getPhone(),
                            "У объектов разное поле phone")
            );

            log.info("Тест positiveTestingSave успешно выполнен");
        }
    }
    @Test
    @DisplayName("Негативное тестирование")
    void negativeTestingSave() {
        log.info("Тест negativeTestingSave начал выполнение");

        mentorJohn.setId(1L);
        final var exception =
                assertThrows(InvalidDataAccessApiUsageException.class,
                        () -> mentorDao.save(mentorJohn), "Неверный exception");

        assertInstanceOf(PersistentObjectException.class, exception.getCause(),
                "Неверный exception");

        log.info("Тест negativeTestingSave успешно выполнен");
    }

    @Test
    @DisplayName("Find all test")
    void positiveFindAllTest() {
        log.info("positive find all test started");

        List<Mentor>  mentors = mentorDao.findAll();
        assertEquals(mentors.size(), 2);
        assertEquals(mentors.get(0).getName(), "Ivan");
        assertEquals(mentors.get(1).getName(), "Petr");
        assertEquals(mentors.get(0).getLastName(), "Ivanovskiy", "Ivanovskiy");
        assertEquals(mentors.get(1).getLastName(), "Petrov", "Petrov");
        assertEquals(mentors.get(0).getId(), 1, "Id Ivanovskiy");
        assertEquals(mentors.get(1).getId(), 2, "Id Petrov");
        assertEquals(mentors.get(0).getPhone(), "88005670011", "phone Ivanovskiy");
        assertEquals(mentors.get(1).getPhone(), "23456787654", "phone Petrov");
//        assertNotEquals(mentors.get(0).getPhone(), mentors.get(1).getPhone());      // почему не работает?
    }
}
