package com.store.app.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="roles")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<UserEntity> users;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name="roles_authorities",
            joinColumns = @JoinColumn(name="roles_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="authorities_id", referencedColumnName = "id") )
    private Collection<AuthoritiesEntity> authorities;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Collection<UserEntity> getUsers() {
        return users;
    }
    public void setUsers(Collection<UserEntity> users) {
        this.users = users;
    }
    public Collection<AuthoritiesEntity> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Collection<AuthoritiesEntity> authorities) {
        this.authorities = authorities;
    }
}
