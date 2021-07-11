package com.rookie.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize=1)
    private Long id;

    private RoleName type;

    public Role(Long id, RoleName type) {
        this.id = id;
        this.type = type;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getType() {
        return type;
    }

    public void setType(RoleName type) {
        this.type = type;
    }
}
