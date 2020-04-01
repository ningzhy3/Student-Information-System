package com.example.demo.DAO;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import com.example.demo.model.Student;
import com.example.demo.model.StudentCourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName:StudentDataService
 * PackgeName:com.example.demo.DAO
 * Description:
 *
 * @Date:2020-03-21 17:18
 * Author:ningzhy3@gmail.com
 */
@Repository
public class StudentDataService {


    private final JdbcTemplate jdbcTemplate;

    public StudentDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Autowired

    public List<Student> selectAllStudents() {
        String sql = "" +
                "SELECT " +
                "student_id, "+
                "first_name, "+
                "last_name, "+
                "email, "+
                "gender "+
                "FROM student";

        return jdbcTemplate.query(sql, mapStudentFromDb());

    }

    public int insertStudent(UUID StudentId, Student student) {
        String sql = "" +
                "INSERT INTO student (student_id, first_name, last_name, email, gender) " +
                "VALUES(?, ?, ?, ?, ?::gender)";
        return jdbcTemplate.update(
                sql,
                StudentId,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getGender().name().toUpperCase()
        );

    }

    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) -> {
            String studentIdStr = resultSet.getString("student_id");
            UUID studentId = UUID.fromString(studentIdStr);
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");

            String genderStr = resultSet.getString("gender").toUpperCase();
            Student.Gender gender = Student.Gender.valueOf(genderStr);

            return new Student(studentId,firstName,lastName,email,gender);

        };
    }

    @SuppressWarnings("ConstantConditions")
    public boolean isEmailTaken(String email) {
        String sql = "" +
                "SELECT EXISTS ( " +
                " SELECT 1 " +
                " FROM student " +
                " WHERE email = ?"+
                    ")";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[] {email} ,
                (resultSet, i) -> resultSet.getBoolean(1)
        );
    }


    public List<StudentCourse> getAllCoursesForStudent(UUID studentId) {
        //TODO

        String sql = "" +
                "SELECT " +
                " student.student_id, " +
                " course.course_id, " +
                " course.name, " +
                " course.description," +
                " course.department," +
                " course.teacher_name," +
                " student_course.start_date, " +
                " student_course.end_date, " +
                " student_course.grade " +
                "FROM student " +
                "JOIN student_course USING (student_id) " +
                "JOIN course         USING (course_id) " +
                "WHERE student.student_id = ?";
        return jdbcTemplate.query(
                sql,
                new Object[]{studentId},
                mapStudentCourseFromDb()
        );

    }

    private RowMapper<StudentCourse> mapStudentCourseFromDb() {
        return (resultSet, i) ->
                new StudentCourse(
                        UUID.fromString(resultSet.getString("student_id")),
                        UUID.fromString(resultSet.getString("course_id")),
                        resultSet.getDate("start_date").toLocalDate(),
                        resultSet.getDate("end_date").toLocalDate(),
                        Optional.ofNullable(resultSet.getString("grade"))
                                .map(Integer::parseInt)
                                .orElse(null),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("department"),
                        resultSet.getString("teacher_name")
                );
    }
}
