package com.cafeManager.dto;

import javax.persistence.*;

/**
 * Created by Martha on 7/29/2016.
 */
@Entity
@Table(name = "tables")
public class TableDTO {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private OrderDTO order;

    @OneToOne
    private UserDTO user;

    public TableDTO() {
    }

    public void setValues(OrderDTO order, UserDTO user) {
        this.order = order;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
