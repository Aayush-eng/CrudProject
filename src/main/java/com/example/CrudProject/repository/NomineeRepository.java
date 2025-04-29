package com.example.CrudProject.repository;

import com.example.CrudProject.entity.Nominee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NomineeRepository extends JpaRepository<Nominee, Long> {
}
