package com.mycompany.siemproject.dto;

import javax.validation.constraints.NotNull;

public class ThresholdDto {
    
    @NotNull
    private Integer id;
    
    @NotNull
    private int number;

    public Integer getId() {
        return id;
    }

    public ThresholdDto(Integer id, int number) {
        this.id = id;
        this.number = number;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
