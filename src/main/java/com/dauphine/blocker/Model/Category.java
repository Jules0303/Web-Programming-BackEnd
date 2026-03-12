package com.dauphine.blocker.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.UUID;


@Entity
@Table(name = "category")
public class Category {

    @Column(name = "name")
    private String name;
    @Id
    @Column(name = "category_id")
    private UUID id;
    public Category(UUID id, String name) {
        this.name = name;
        this.id = id;
    }

    public Category() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


}
