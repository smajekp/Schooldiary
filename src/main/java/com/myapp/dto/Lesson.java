package com.myapp.dto;

/**
 * Created by Piotrek on 2016-06-04.
 */
public class Lesson extends CommonDto {

    String subject;

    String topic;

    String date;

    public Lesson(String subject, String topic, String date) {
        this.subject = subject;
        this.topic = topic;
        this.date = date;
    }

    public Lesson(Integer id, String subject, String topic, String date) {
        this(subject, topic, date);
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
