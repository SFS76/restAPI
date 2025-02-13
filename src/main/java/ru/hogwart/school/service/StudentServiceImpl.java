package ru.hogwart.school.service;

import org.springframework.stereotype.Service;
import ru.hogwart.school.entities.CountStudentByFaculty;
import ru.hogwart.school.entities.Student;
import ru.hogwart.school.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.debug("Was create student {}", student);
        return studentRepository.save(student);
    }

    public Optional<Student> findStudent(long id) {
        logger.info("Was find student id{}", id);
        return studentRepository.findById(id);
    }

    public Student editStudent(long id, Student student) {
        logger.info("Was change student id{}", id);
        logger.debug("Was change student {}", student);
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was delete student id{}", id);
        studentRepository.deleteById(id);
    }

    public List<Student> getAllByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> findByAgeBetween(int from, int to) {
        return studentRepository.findByAgeBetween(from, to);
    }

    public Integer countStudent() {return studentRepository.countStudent();}

    public Integer avgAgeStudent() {return studentRepository.avgAgeStudent();}

    public List<Student> last5Student() {return studentRepository.last5Student();}
}
