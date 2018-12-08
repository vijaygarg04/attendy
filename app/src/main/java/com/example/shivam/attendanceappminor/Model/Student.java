package com.example.shivam.attendanceappminor.Model;

public class Student {
    String studentname;
    String studendid;
    String studentdob;
    String password;
    String batchid;
    String batchstartyear;
    String batchendyear;
    String stream;
    String mobilenumber;
    String gender;
    String course;

    public Student(String studentname, String studendid, String studentdob, String password, String batchid, String batchstartyear, String batchendyear, String stream, String mobilenumber, String gender, String course) {
        this.studentname = studentname;
        this.studendid = studendid;
        this.studentdob = studentdob;
        this.password = password;
        this.batchid = batchid;
        this.batchstartyear = batchstartyear;
        this.batchendyear = batchendyear;
        this.stream = stream;
        this.mobilenumber = mobilenumber;
        this.gender = gender;
        this.course = course;
    }

    public Student() {
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudendid() {
        return studendid;
    }

    public void setStudendid(String studendid) {
        this.studendid = studendid;
    }

    public String getStudentdob() {
        return studentdob;
    }

    public void setStudentdob(String studentdob) {
        this.studentdob = studentdob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

    public String getBatchstartyear() {
        return batchstartyear;
    }

    public void setBatchstartyear(String batchstartyear) {
        this.batchstartyear = batchstartyear;
    }

    public String getBatchendyear() {
        return batchendyear;
    }

    public void setBatchendyear(String batchendyear) {
        this.batchendyear = batchendyear;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
