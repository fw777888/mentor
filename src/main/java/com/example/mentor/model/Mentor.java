package com.example.mentor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String phone;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
