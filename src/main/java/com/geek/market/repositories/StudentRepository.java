package com.geek.market.repositories;

import com.geek.market.entities.Product;
import com.geek.market.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student,Long>, JpaSpecificationExecutor<Student> {
}