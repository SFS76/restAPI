package ru.hogwart.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwart.school.entities.CountStudentByFaculty;
import ru.hogwart.school.entities.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);
    List<Student> findByAgeBetween(int from, int to);
    @Query(value = "SELECT COUNT(id) FROM student", nativeQuery = true)
    Integer countStudent();

    @Query(value = "SELECT * FROM student ORDER BY ID DESC LIMIT 5", nativeQuery = true)
    List<Student> last5Student();
}
