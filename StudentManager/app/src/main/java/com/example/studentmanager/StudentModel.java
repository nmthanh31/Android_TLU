package com.example.studentmanager;

import java.util.Date;

public class StudentModel {

    private String id;

    private String name;

    private String birthday;

    private String classes;

    public StudentModel() {
    }

    public StudentModel(String id, String name, String birthday, String classes) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.classes = classes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }
}
