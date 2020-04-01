package com.example.demo.model;

import java.time.LocalDate;
import java.util.UUID;

/**
 * ClassName:StudentCourse
 * PackgeName:com.example.demo.model
 * Description:
 *
 * @Date:2020-03-25 20:16
 * Author:ningzhy3@gmail.com
 */
public class StudentCourse {
    private final UUID studentId;
    private final UUID courseId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Integer grade;

    private final String name;
    private final String description;
    private final String department;
    private final String teacherName;

    public StudentCourse(UUID studentId,
                         UUID courseId,
                         LocalDate startDate,
                         LocalDate endDate,
                         Integer grade,
                         String name,
                         String description,
                         String department,
                         String teacherName) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
        this.name = name;
        this.description = description;
        this.department = department;
        this.teacherName = teacherName;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDepartment() {
        return department;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
