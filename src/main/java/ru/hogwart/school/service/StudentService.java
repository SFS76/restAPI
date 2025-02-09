package ru.hogwart.school.service;

import org.springframework.stereotype.Service;
import ru.hogwart.school.entities.Student;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {

    Student addStudent(Student student);
    Optional<Student> findStudent(long id);
    Student editStudent(long id, Student student);
    void deleteStudent(long id);
    List<Student> getAllByAge(int age);
    List<Student> findByAgeBetween(int from, int to);
    Integer countStudent();
    Integer avgAgeStudent();
    List<Student> last5Student();
}
