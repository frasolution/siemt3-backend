package com.mycompany.siemproject.event;

import com.mycompany.siemproject.model.User;
import org.springframework.context.ApplicationEvent;

public class OnForgotPasswordEvent extends ApplicationEvent {
    private User user;

    public OnForgotPasswordEvent(User user) {
        super(user);        
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
    
}
