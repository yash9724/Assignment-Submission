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
public class FileDTO {
    private String fileId;
    private String fileName;
    private String subject;
    private String description;
    private String timeStamp;
    private String filePath;
    private String fileType;
    private String semester;

    public FileDTO(String fileId, String fileName, String description, String timeStamp, String fileType,String semester) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.description = description;
        this.timeStamp = timeStamp;
        this.fileType = fileType;
        this.semester = semester;
    }
    
    public FileDTO() {
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
