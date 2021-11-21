package com.example.persist.entity;
//project -содержит информацию о проектах
//  id - уникальный идентификатор
//  name - название проекта

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "products" )
//    List<Device> products;

    @OneToMany(mappedBy = "project")
    List<Device> devices;


    public Project() {
    }

    public Project(Integer id, String name, List<Device> devices) {
        this.id = id;
        this.name = name;
        this.devices = devices;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
