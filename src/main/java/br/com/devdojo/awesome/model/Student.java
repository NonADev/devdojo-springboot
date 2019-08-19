package br.com.devdojo.awesome.model;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Student {
    private int pk_id;
    private String name;
    public static List<Student> studentList;

    static{
        studentRepository();
    }

    public Student() {
    }

    public Student(int pk_id, String name) {
        this.pk_id = pk_id;
        this.name = name;
    }

    private static void studentRepository(){
        studentList = new ArrayList<Student>(Arrays.asList(new Student(1, "Todoroki"),new Student(2, "Amanato"), new Student(3, "Midoriya")));
    }

    @Override
    public boolean equals(Object o) { //retorna true comparando id com o compo selecionado (pk_id).
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return pk_id == student.pk_id;
    }

    @Override
    public int hashCode() { //coloca id de listas como pk_id
        return Objects.hash(pk_id);
    }

    public int getPk_id() {
        return pk_id;
    }

    public void setPk_id(int pk_id) {
        this.pk_id = pk_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
