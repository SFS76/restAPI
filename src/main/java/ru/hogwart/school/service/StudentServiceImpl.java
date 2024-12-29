package ru.hogwart.school.service;

import org.springframework.stereotype.Service;
import ru.hogwart.school.entities.Student;

import java.util.HashMap;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long count = 0;

    public Student addStudent(Student student) {
        student.setId(count++);
        students.put(student.getId(), student);
        return student;
    }

    public Student findStudent(long id) {
        return students.get(id);
    }

    public Student editStudent(long id, Student student) {
        if (!students.containsKey(id)) {
            return null;
        }
        students.put(id, student);
        return student;
    }

    public void deleteStudent(long id) {
        students.remove(id);
    }

    public List<Student> getAllByAge(int age) {
        return students.values().stream().filter(it -> it.getAge() == age).toList();
    }
}
