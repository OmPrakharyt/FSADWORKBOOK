package com.klu.courseapp;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CourseService {

    private List<Course> courseList = new ArrayList<>();

    public List<Course> getAllCourses() {
        return courseList;
    }

    public Course getCourseById(int id) {
        for (Course c : courseList) {
            if (c.getCourseId() == id) {
                return c;
            }
        }
        return null;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public boolean updateCourse(int id, Course updatedCourse) {
        Course existing = getCourseById(id);
        if (existing != null) {
            existing.setTitle(updatedCourse.getTitle());
            existing.setDuration(updatedCourse.getDuration());
            existing.setFee(updatedCourse.getFee());
            return true;
        }
        return false;
    }

    public boolean deleteCourse(int id) {
        return courseList.removeIf(c -> c.getCourseId() == id);
    }

    public List<Course> searchByTitle(String title) {
        List<Course> result = new ArrayList<>();
        for (Course c : courseList) {
            if (c.getTitle().equalsIgnoreCase(title)) {
                result.add(c);
            }
        }
        return result;
    }
}