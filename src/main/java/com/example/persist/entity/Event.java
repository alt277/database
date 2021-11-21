package com.example.persist.entity;
/*  event - содержит информацию о истории событий и ошибках, которые возникали на устройствах
     id - уникальный идентификатор события
     device_id - идентификатор устройства в таблице devices
     date - дата возникновения события (UTC)
     type - (enum) - тип события. Поле может принимать значения: event , warning или ли error
     is_read - (boolean) - прочитано событие или нет
*/

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Event" )
public class Event {
   enum  Type {Event, Warning, Error}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name="date")
    private Date date;

    @Column(name = "type")
    private Type type;

    @Column(name=" is_read")
    private boolean isRead;


        public Event(Integer id, Device device, Date date, Type type, boolean isRead) {
        this.id = id;
        this.device = device;
        this.date = date;
        this.type = type;
        this.isRead = isRead;
    }


    public Event() {
    }

    public Event(Integer id, Device device, Date date, boolean isRead) {
        this.id = id;
        this.device = device;
        this.date = date;
        this.isRead = isRead;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

}
