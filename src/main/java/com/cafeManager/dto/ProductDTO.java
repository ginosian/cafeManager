package com.cafeManager.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Martha on 7/29/2016.
 */
@Entity
@Table(name = "products")
public class ProductDTO {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public ProductDTO(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
