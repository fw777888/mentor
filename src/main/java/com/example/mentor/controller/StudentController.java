package com.example.mentor.controller;

import com.example.mentor.dao.Dao;
import com.example.mentor.entity.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private Dao<Long, Mentor> studentDao;
    @GetMapping("/info_student")
    String findAll(Model model) {
        model.addAttribute("students", studentDao.findAll());
        return "student";
    }
}
