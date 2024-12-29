package ru.hogwart.school.service;

import org.springframework.stereotype.Service;
import ru.hogwart.school.entities.Student;

import java.util.List;

@Service
public interface StudentService {

    Student addStudent(Student student);
    Student findStudent(long id);
    Student editStudent(long id, Student student);
    void deleteStudent(long id);
    List<Student> getAllByAge(int age);
}
