package com.meva.finance.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue
    private Integer id;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategorys;

}
