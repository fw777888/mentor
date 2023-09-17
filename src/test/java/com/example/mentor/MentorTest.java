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
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Sql("/mentor.sql")
@DisplayName("Testing MentorDao")
public class MentorTest {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MentorTest.class);
    private final Dao<Long, Mentor> mentorDao;
    private final Mentor mentorJohn = Mentor
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
        @DisplayName("Позитивное тестирование")
        void positiveTestingSave() {
            log.info("Тест positiveTestingSave начал выполнение");

            mentorDao.save(mentorJohn);
            final var mentorJohnOptional = mentorDao
                    .findBYId(2L)
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

        assertInstanceOf(PersistentObjectException.class, exception.getCause(), "Неверный exception");

        log.info("Тест negativeTestingSave успешно выполнен");
    }
}
