package ru.hogwart.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwart.school.entities.CountStudentByFaculty;
import ru.hogwart.school.service.StudentServiceImpl;

import java.util.List;

@RestController
public class CountStudentByFacultyController {
    private final StudentServiceImpl studentService;

    public CountStudentByFacultyController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student-by-faculty")
    public List<CountStudentByFaculty> countStudentByFaculties() {
        return studentService.countStudentByFaculties();
    }
}
