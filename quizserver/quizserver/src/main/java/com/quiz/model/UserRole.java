package com.quiz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UserRoleId;

    //user
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne
    private Role role;
}
