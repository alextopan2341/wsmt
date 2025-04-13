package com.example.wsmt.model.dto;

import lombok.Data;

@Data
public class TaskDTO {
    private String title;
    private String email;
    private String description;
    private boolean completed;
}
