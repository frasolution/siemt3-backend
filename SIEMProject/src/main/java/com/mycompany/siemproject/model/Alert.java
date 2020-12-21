package com.mycompany.siemproject.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    private String eventId;
    
    @NotNull
    private String eventType;
    
    @NotNull
    private String eventName;
    
    @NotNull
    private LocalDateTime timestamp;
   
    private String priority;
    private String customData;

    public Alert() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

    public Integer getId() {
        return id;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getPriority() {
        return priority;
    }

    public String getCustomData() {
        return customData;
    }

}
