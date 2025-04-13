package com.example.wsmt.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="completed")
    private boolean completed;

    public Task() {}
    public Task(User user, String title, String description, boolean completed) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

}
