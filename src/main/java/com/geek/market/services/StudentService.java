package com.geek.market.services;


import com.geek.market.entities.Product;
import com.geek.market.entities.Student;
import com.geek.market.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findID(Long id) {
        return studentRepository.findById(id);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void delete(Student student){
        studentRepository.delete(student);
    }

}
