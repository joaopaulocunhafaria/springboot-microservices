package com.ms.user.dtos;

import java.util.UUID;

public class EmailDto {

    private UUID uuid;
    private String emailTo;
    private String subject;
    private String text;


 
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
    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    

    
}
