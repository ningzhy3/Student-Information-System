package com.example.demo.Controller;

import com.example.demo.Exception.ApiRequestException;
import com.example.demo.model.Student;
import com.example.demo.model.StudentCourse;
import com.example.demo.studentservice.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * ClassName:StudentController
 * PackgeName:com.example.demo.Controller
 * Description:
 *
 * @Date:2020-03-20 11:18
 * Author:ningzhy3@gmail.com
 */
@RestController
@RequestMapping("api/students")

public class StudentController {

    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getALlStudent () {

        return studentService.getAllStudents();
    }

    @PostMapping
    public void addNewStudent(@RequestBody @Valid Student student) {

        studentService.addNewStudent(student);
    }

    @GetMapping(path = "{studentId}/courses")
    public List<StudentCourse> getAllCoursesForStudent(@PathVariable("studentId") UUID studentId) {
        //TODO

        return studentService.getAllCoursesForStudent(studentId);
    }



}
