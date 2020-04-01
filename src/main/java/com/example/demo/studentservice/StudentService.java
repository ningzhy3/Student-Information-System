package com.example.demo.studentservice;

import com.example.demo.DAO.StudentDataService;
import com.example.demo.EmailValidator;
import com.example.demo.Exception.ApiRequestException;
import com.example.demo.model.Student;
import com.example.demo.model.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * ClassName:StudentService
 * PackgeName:com.example.demo.studentservice
 * Description:
 *
 * @Date:2020-03-21 17:10
 * Author:ningzhy3@gmail.com
 */
@Service
public class StudentService {

    private final StudentDataService studentDataService;

    private final EmailValidator emailValidator;

    @Autowired
    public StudentService(StudentDataService studentDataService, EmailValidator emailValidator) {
        this.studentDataService = studentDataService;
        this.emailValidator = emailValidator;
    }




    public List<Student> getAllStudents() {
        return studentDataService.selectAllStudents();
    }



    public void addNewStudent(Student student) {
        addNewStudent(null, student);
    }

    private void addNewStudent(UUID studentId, Student student) {
            if (!emailValidator.test(student.getEmail())) {
            throw new ApiRequestException(student.getEmail() + " is not valid");
                }
        UUID newStudentId = Optional.ofNullable(studentId).orElse(UUID.randomUUID());

//TODO: validate email
        //TODO :verfiy that email is not taken

        if (studentDataService.isEmailTaken(student.getEmail())) {
            throw new ApiRequestException(student.getEmail() + " is taken ");
        }

        studentDataService.insertStudent(newStudentId,student);
    }


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }


    public List<StudentCourse> getAllCoursesForStudent(UUID studentId) {
        //TODO

        return studentDataService.getAllCoursesForStudent(studentId);
    }




}
