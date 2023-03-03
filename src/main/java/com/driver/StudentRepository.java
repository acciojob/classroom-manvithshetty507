package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    Map<String,Student> studentsDb = new HashMap<>();
    Map<String,Teacher> teachersDb = new HashMap<>();
    Map<String, List<Student>> teacherstudentDb = new HashMap<>();
    public void addStudent(Student student) {
        studentsDb.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teachersDb.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {

        if(studentsDb.containsKey(student) && teachersDb.containsKey(teacher)){

            List<Student> list = new ArrayList<>();
            if(teacherstudentDb.containsKey(teacher)){
                list = teacherstudentDb.get(teacher);
            }
            list.add(studentsDb.get(student));
            teachersDb.get(teacher).setNumberOfStudents(list.size());
            teacherstudentDb.put(teacher,list);
        }
    }

    public Student getStudentByName(String name) {
        if(studentsDb.containsKey(name))
            return studentsDb.get(name);
        return null;
    }

    public Teacher getTeacherByName(String name) {
        if(teachersDb.containsKey(name))
            return teachersDb.get(name);
        return null;
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> ans = new ArrayList<>();
        if(teacherstudentDb.containsKey(teacher)){
            for(Student s:teacherstudentDb.get(teacher)){
                ans.add(s.getName());
            }
        }
        return ans;
    }

    public List<String> getAllStudents() {
        List<String> ans = new ArrayList<>();
        for(String s : studentsDb.keySet()){
            ans.add(s);
        }
        return ans;
    }

    public void deleteTeacherByName(String teacher) {
        if(teachersDb.containsKey(teacher))
            teachersDb.remove(teacher);
        if(teacherstudentDb.containsKey(teacher))
            teacherstudentDb.remove(teacher);
    }

    public void deleteAllTeachers() {
        teachersDb.clear();
        studentsDb.clear();
        teacherstudentDb.clear();
    }
}
