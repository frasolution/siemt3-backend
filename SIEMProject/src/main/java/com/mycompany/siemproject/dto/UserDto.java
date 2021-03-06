package com.mycompany.siemproject.dto;

import com.mycompany.siemproject.validation.ValidEmail;
import javax.validation.constraints.NotNull;

public class UserDto {
    
    @NotNull
    private String firstName;
    
    @NotNull
    private String lastName;
    
    @ValidEmail
    @NotNull
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
