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
public class MarksDTO {
    private String rollNo;
    private String semester;
    private String subject;
    private String midsem1;
    private String midsem2;
    private String midsem3;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMidsem1() {
        return midsem1;
    }

    public void setMidsem1(String midsem1) {
        this.midsem1 = midsem1;
    }

    public String getMidsem2() {
        return midsem2;
    }

    public void setMidsem2(String midsem2) {
        this.midsem2 = midsem2;
    }

    public String getMidsem3() {
        return midsem3;
    }

    public void setMidsem3(String midsem3) {
        this.midsem3 = midsem3;
    }
    
}
