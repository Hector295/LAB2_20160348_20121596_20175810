package com.ipt.dashboard.entity;

import javax.persistence.*;

@Entity
@Table(name="areas")
public class Areas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idarea;

    @Column(name="nombreArea")
    private String nombrearea;

    public int getIdarea() {
        return idarea;
    }

    public void setIdarea(int idarea) {
        this.idarea = idarea;
    }

    public String getNombrearea() {
        return nombrearea;
    }

    public void setNombrearea(String nombreArea) {
        this.nombrearea = nombreArea;
    }
}
