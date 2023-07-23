package com.meva.finance.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sub_category")
public class SubCategory {

    @Id
    @GeneratedValue
    @Column(name = "id_sub_category")
    private Integer id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;


}
