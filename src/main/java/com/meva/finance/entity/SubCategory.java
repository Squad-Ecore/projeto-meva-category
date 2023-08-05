package com.meva.finance.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sub_category")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_category_id_sub_category_seq")
    @SequenceGenerator(name = "sub_category_id_sub_category_seq", sequenceName = "sub_category_id_sub_category_seq", allocationSize = 1)
    @Column(name = "id_sub_category")
    private Integer id;
    private String description;

    @ManyToOne()
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    public SubCategory() {
    }

    public SubCategory(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
