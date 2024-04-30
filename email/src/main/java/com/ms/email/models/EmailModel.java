package com.ms.email.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ms.email.enums.StatusEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_EMAILS")
public class EmailModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; 
    private UUID userId;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column( columnDefinition =  "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public String getEmailFrom() {
        return emailFrom;
    }
    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }
    public String getEmailTo() {
        return emailTo;
    }
    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public LocalDateTime getSendDateEmail() {
        return sendDateEmail;
    }
    public void setSendDateEmail(LocalDateTime sendDateEmail) {
        this.sendDateEmail = sendDateEmail;
    }
    public StatusEmail getStatusEmail() {
        return statusEmail;
    }
    public void setStatusEmail(StatusEmail statusEmail) {
        this.statusEmail = statusEmail;
    }
    

    




}
