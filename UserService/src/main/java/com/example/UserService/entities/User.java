package com.example.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "micro_users")
@Getter
@Setter
public class User {
    //id , name, email, about
    @Id
    @Column(name = "ID", nullable = false)
    private String userId;
    @Column(name = "NAME",length = 20)
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT")
    private String about;
    @Transient
    private List<Ratings> ratings=new ArrayList<>();

}

