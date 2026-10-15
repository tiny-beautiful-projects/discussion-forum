package com.example.discussion_forum.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
public class Coffee {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private int price;
}
