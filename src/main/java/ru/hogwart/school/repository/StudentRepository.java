package ru.hogwart.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwart.school.entities.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);
    List<Student> findByAgeBetween(int from, int to);
}
