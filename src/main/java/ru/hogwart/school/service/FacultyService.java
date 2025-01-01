package ru.hogwart.school.service;

import org.springframework.stereotype.Service;
import ru.hogwart.school.entities.Faculty;

import java.util.List;
import java.util.Optional;

@Service
public interface FacultyService {
    Faculty addFaculty(Faculty faculty);
    Optional<Faculty> findFaculty(long id);
    Faculty editFaculty(long id, Faculty faculty);
    void deleteFaculty(long id);
    List<Faculty> getAllByColor(String color);
}
