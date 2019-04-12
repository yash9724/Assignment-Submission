/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.dto;

/**
 *
 * @author user
 */
public class FacultyDTO {
    private String facultyName;
    private String contactNo;
    private String email;
    private String address;
    private String photoPath;
    private String username;

    public FacultyDTO(String facultyName, String contactNo, String email, String address, String photoPath) {
        this.facultyName = facultyName;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
        this.photoPath = photoPath;
    }
    public FacultyDTO(){}
    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
}
