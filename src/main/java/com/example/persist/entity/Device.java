package com.example.persist.entity;

import javax.persistence.*;
import java.util.List;

/*      device - содержит информацию об устройствах

         id - уникальный идентификатор устройства
         project_id - идентификатор проекта в таблице projects
         serial_number - уникальный серийный номер устройства, например: A4BCC
         */
@Entity
@Table(name = "Device" )
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "project_id")
//    private Project project_id;

    @Column(name=" serial_number")
    private String serialNumber;

    @OneToMany(mappedBy = "device")
    List<Event> events;

    @ManyToOne
    @JoinColumn (name = "project_id" )
    private Project project ;

    public Device() {
    }

    public Device(Integer id, String serialNumber, List<Event> events, Project project) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.events = events;
        this.project = project;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }



    public Project getProject() {
        return project;
    }
}
