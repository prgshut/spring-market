package com.geek.market.controllers;

import com.geek.market.entities.Student;
import com.geek.market.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;
    @GetMapping
    public String allStudent(Model model) {

        model.addAttribute("students", studentService.findAll());

        return "students";
    }


    @GetMapping("/change/{id}")
    public String updateStudent(Model model, @PathVariable Long id) {
        model.addAttribute("student", studentService.findID(id).get());
        return "edit_student";
    }

    @PostMapping("/change")
    public String studentChange( @ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/remove/{id}")
    public String removeStudent(@PathVariable Long id){
        Student student= studentService.findID(id).get();
        studentService.delete(student);
        return "redirect:/students";
    }

    @GetMapping("/add")
    public String studentAdd(Model model){
        model.addAttribute("student", new Student());
        return "add_student";
    }
    @PostMapping("/add")
    public String studentSave(@ModelAttribute Student student){
        student.setId(null);
        studentService.save(student);
        return "redirect:/students";
    }

}
