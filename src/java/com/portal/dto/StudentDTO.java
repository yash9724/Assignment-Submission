package com.portal.dto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class StudentDTO {
    private String studentName;
    private String rollNo;
    private String semester;
    private String contactNo;
    private String email;
    private String address;
    private String photoPath;

    public StudentDTO(String studentName, String rollNo, String semester, String contactNo, String email, String address, String photoPath) {
        this.studentName = studentName;
        this.rollNo = rollNo;
        this.semester = semester;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
        this.photoPath = photoPath;
    }

    public StudentDTO() {
    }
    
    

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
