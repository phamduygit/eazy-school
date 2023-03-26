package com.example.learningspringboot.model;

public class Contact {
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;

    public Contact(String name, String mobileNum, String email, String subject, String message) {
        this.name = name;
        this.mobileNum = mobileNum;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNum() {
        return this.mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", mobileNum='" + getMobileNum() + "'" +
            ", email='" + getEmail() + "'" +
            ", subject='" + getSubject() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
}