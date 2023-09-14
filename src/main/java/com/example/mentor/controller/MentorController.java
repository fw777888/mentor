package com.example.mentor.controller;

import com.example.mentor.dao.Dao;
import com.example.mentor.dao.MentorDao;
import com.example.mentor.model.Mentor;
import com.example.mentor.util.HibernateUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    private  MentorDao mentorDao;
    private Dao<Long, Mentor> mentorDao;

    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    @GetMapping("/info_mentor")
    String findAll() {
        return "mentor";
     }
}
