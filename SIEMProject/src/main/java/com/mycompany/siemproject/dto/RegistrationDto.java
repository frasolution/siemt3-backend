package com.mycompany.siemproject.dto;

import com.mycompany.siemproject.validation.ValidPassword;
import javax.validation.constraints.NotNull;

public class RegistrationDto {
    
    @ValidPassword
    @NotNull
    private String password;
    
    @NotNull
    private String token;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}
