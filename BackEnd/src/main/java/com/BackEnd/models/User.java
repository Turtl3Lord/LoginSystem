package com.BackEnd.models;

import com.BackEnd.utils.IdGenerator;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(length = 64)
    private String id;     // Not generated — set manually

    @Column(nullable = false, length = 255)
    private String name;

    @Column(name = "created_at",
            updatable = false,
            insertable = false)
    private LocalDateTime createdAt;  // Managed by DB default CURRENT_TIMESTAMP

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id ="usr_" + IdGenerator.generateId();
        }
    }

    public User() {}

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
