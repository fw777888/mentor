package com.example.mentor.controller;

import com.example.mentor.dao.Dao;
import com.example.mentor.entity.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    private Dao<Long, Mentor> mentorDao;
    @GetMapping("/info_mentor")
    String findAll(Model model) {
        model.addAttribute("mentors", mentorDao.findAll());
        return "mentor";
     }
}
