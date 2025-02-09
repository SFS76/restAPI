package ru.hogwart.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwart.school.entities.Faculty;
import ru.hogwart.school.service.FacultyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {this.facultyService = facultyService;}

    @GetMapping("{id}")
    public ResponseEntity<Optional<Faculty>> getFacultyInfo(@PathVariable Long id) {
        Optional<Faculty> faculty = facultyService.findFaculty(id);
        if (faculty.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {return facultyService.addFaculty(faculty);}

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(id, faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping ("find")
    public List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String query) {
        return facultyService.findByNameIgnoreCaseOrColorIgnoreCase(query, query);
    }
}
