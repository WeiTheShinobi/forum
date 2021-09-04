package com.weitheshinobi.forum.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
