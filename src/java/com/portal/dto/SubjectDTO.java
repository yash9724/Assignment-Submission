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
public class SubjectDTO {
    private String subjectCode;
    private String subjectName;
    private String semester;
    private String faculty_A;
    private String faculty_B;

    public SubjectDTO(String subjectCode, String subjectName, String semester, String faculty_A, String faculty_B) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.semester = semester;
        this.faculty_A = faculty_A;
        this.faculty_B = faculty_B;
    }

    public SubjectDTO() {
    }
    
    

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getFaculty_A() {
        return faculty_A;
    }

    public void setFaculty_A(String faculty_A) {
        this.faculty_A = faculty_A;
    }

    public String getFaculty_B() {
        return faculty_B;
    }

    public void setFaculty_B(String faculty_B) {
        this.faculty_B = faculty_B;
    }
}
