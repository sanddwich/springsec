package com.example.springsec.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(indexes = {
        @Index(name = "nameIndex", columnList = "name"),
        @Index(name = "codeIndex", columnList = "code"),
        @Index(name = "descriptionIndex", columnList = "description")
})
public class AccessRole extends AbstractEntity {
    public AccessRole() {}

    public AccessRole(@NotEmpty String name, @NotEmpty String code, @NotEmpty String description, List<Privilege> privileges) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.privileges = privileges;
    }

    @NotEmpty
    @Column(unique = true)
    private String name;

    @NotEmpty
    @Column(unique = true)
    private String code;

    @NotEmpty
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "user_access_role_lnk",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinTable(
            name = "access_role_privilege_lnk",
            joinColumns = {@JoinColumn(name = "access_role_id")},
            inverseJoinColumns = {@JoinColumn(name = "privilege_id")}
    )
    private List<Privilege> privileges = new ArrayList<>();

    @Override
    public String toString() {
        return "\nAccessRole{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                ", privileges=" + privileges +
                '}';
    }
}
