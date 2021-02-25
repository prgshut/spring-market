package com.geek.market.controllers;

import com.geek.market.entities.Product;
import com.geek.market.entities.Student;
import com.geek.market.services.StudentService;
import com.geek.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;
    @GetMapping
    public String allStudent(Model model) {

        model.addAttribute("student", studentService.findAll());

        return "product";
    }

}
