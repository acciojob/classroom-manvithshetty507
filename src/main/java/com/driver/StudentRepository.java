package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentDB = new HashMap<>();//Database for student
    HashMap<String, Teacher> teacherDB = new HashMap<>();//Database for teacher
    HashMap<String, List<String>> student_teacherDB = new HashMap<>(); //Database for student Teacher pair

    public void addStudent(Student student){
        studentDB.put(student.getName(), student);
    }
    public void addTeacher(Teacher teacher){
        teacherDB.put(teacher.getName(), teacher);
    }
    public void addStudentTeacherPair(String student, String teacher){
        if (student_teacherDB.containsKey(teacher)){
            List<String> teacher_list = student_teacherDB.get(teacher);
            teacher_list.add(student);
            student_teacherDB.put(teacher,teacher_list);
        }else {
            List<String> teacher_list = new ArrayList<>();
            teacher_list.add(student);
            student_teacherDB.put(teacher,teacher_list);
        }
    }
    public Student getStudentByName(String name){
        return studentDB.get(name);
    }
    public Teacher getTeacherByName(String name){
        return teacherDB.get(name);
    }
    public List<String> getStudentsByTeacherName(String teacher){
        return student_teacherDB.get(teacher);
    }
    public List<String> getAllStudents(){
        List<String> list = new ArrayList<>();
        for(String s: studentDB.keySet()){
            list.add(s);
        }
        return list;
    }
    public void deleteTeacherByName(String teacher){
        List<String> student_list = student_teacherDB.get(teacher);
        for (String s: student_list){
            studentDB.remove(s);
        }
        teacherDB.remove(teacher);
        student_teacherDB.remove(teacher);
    }
    public void deleteAllTeachers(){
        for (List<String> student_list: student_teacherDB.values()){
            for (String s: student_list){
                studentDB.remove(s);
            }
        }
        teacherDB.clear();
        student_teacherDB.clear();
    }
}