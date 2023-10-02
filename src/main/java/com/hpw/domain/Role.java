package com.hpw.domain;

import com.hpw.domain.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType type;

    @Override
    public String toString() {
        return "Role [type=" + type + "]";
    }
}
