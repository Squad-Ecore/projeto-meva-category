package com.meva.finance.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_category")
    private Integer id;
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonIgnoreProperties("category")
    private List<SubCategory> subCategories = new ArrayList<>();

    public Category() {
    }

    public Category(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
