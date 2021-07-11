package com.rookie.ecommerce.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize=1)
    private Long id;

    @Size(min = 3, max = 50)
    private String fullname;

    @NotBlank
    @Size(min = 3, max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 30)
    private String password;

    @Size(min = 4)
    private String phonenum;

    @NotBlank
    private String status;

    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "roleid"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String fullname, String email, String password, String phonenum, String status, String address, Set<Role> roles) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phonenum = phonenum;
        this.status = status;
        this.address = address;
        this.roles = roles;
    }

    public User(Long id, String fullname, String email, String password, String phonenum, String status, String address, Set<Role> roles) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phonenum = phonenum;
        this.status = status;
        this.address = address;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", roles=" + roles +
                '}';
    }
}
