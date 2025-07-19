package com.annotations.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    Integer userId;

    String name;

    String email;

    String passwordHash;

    @CreationTimestamp
    Timestamp createdAt;

    @CreationTimestamp
    Timestamp updatedAt;
}
