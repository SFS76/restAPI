package ru.hogwart.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwart.school.entities.Faculty;
import ru.hogwart.school.entities.Student;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findByColor(String color);

    List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);
}
