package com.cafeManager.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Martha on 7/29/2016.
 */
@Entity
@Table(name = "roles")
public class RoleDTO {

    @Id
    @GeneratedValue
    private Long id;

    private String role;

    public RoleDTO() {
    }

    public RoleDTO(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
