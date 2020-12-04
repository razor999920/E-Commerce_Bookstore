package com.mnp.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnp.store.domain.constants.ROLE;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Role.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ROLE name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ROLE getName() {
        return name;
    }

    public Role name(ROLE name) {
        this.name = name;
        return this;
    }

    public void setName(ROLE name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Role users(Set<User> users) {
        this.users = users;
        return this;
    }

    public Role addUser(User user) {
        this.users.add(user);
        user.getRoles().add(this);
        return this;
    }

    public Role removeUser(User user) {
        this.users.remove(user);
        user.getRoles().remove(this);
        return this;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        return id != null && id.equals(((Role) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Role{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                "}";
    }
}


