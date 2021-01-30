package com.store.app.database.entity;


import com.store.app.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "users")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column
    @NotEmpty
    private String publicUserId;

    @Column
    @NotEmpty
    private String firstName;

    @Column
    @NotEmpty
    private String lastName;

    @Column
    @NotEmpty
    private String address;

    @Column
    @NotEmpty
    private String city;

    @Column
    @NotEmpty
    @Digits(fraction = 0, integer = 9)
    private String telephone;

    @Column
    private String email;

    @Column
    @NotEmpty
    private String encryptedPassword;
    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<OrderEntity> orders;
    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<CartEntity> cart;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private Collection<RoleEntity> roles;

    public UserEntity() {
    }

    public UserEntity(UserDto userDto) {
        BeanUtils.copyProperties(userDto, this);
    }
}
