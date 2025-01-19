package ru.hogwart.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.hogwart.school.entities.Student;
import ru.hogwart.school.service.AvatarService;
import ru.hogwart.school.service.StudentService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(StudentController.class)
public class StudentControllerWebMvc {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private StudentService studentService;

    @MockitoBean
    private AvatarService avatarService;

    @Test
    void shouldCreateStudent() throws Exception {
        // given
        Long studentID = 1L;
        Student student = new Student("Name", 20);
        Student savedStudent = new Student("Name", 20);
        savedStudent.setId(studentID);

        when(studentService.addStudent(student)).thenReturn(savedStudent);

        // when
        ResultActions perform = mockMvc.perform(post("/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));

        // then
        perform
                .andExpect(jsonPath("$.id").value(savedStudent.getId()))
                .andExpect(jsonPath("$.name").value(savedStudent.getName()))
                .andExpect(jsonPath("$.age").value(savedStudent.getAge()))
                .andDo(print());
    }

    @Test
    void shouldGetStudent() throws Exception {
        // given
        Long studentID = 1L;
        Student student = new Student("Name", 20);

        when(studentService.findStudent(studentID)).thenReturn(Optional.of(student));

        // when
        ResultActions perform = mockMvc.perform(get("/student/{id}", studentID));

        // then
        perform
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()))
                .andDo(print());
    }

    @Test
    void shouldUpdateStudent() throws Exception {
        // given
        Long studentID = 1L;
        Student student = new Student("Name", 20);

        when(studentService.editStudent(studentID, student)).thenReturn(student);

        // when
        ResultActions perform = mockMvc.perform(put("/student/{id}", studentID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));

        // then
        perform
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()))
                .andDo(print());
    }

}
