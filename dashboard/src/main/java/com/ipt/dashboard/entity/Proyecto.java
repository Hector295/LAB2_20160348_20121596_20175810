package com.ipt.dashboard.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="proyectos")
public class Proyecto {

    @Id
    @Column(nullable = false)
    private int proyectoid;
    private String proyectonombre;
    @Column(nullable = false)
    private String usuariocorreo;

    public int getProyectoid() {
        return proyectoid;
    }

    public void setProyectoid(int proyectoid) {
        this.proyectoid = proyectoid;
    }

    public String getProyectonombre() {
        return proyectonombre;
    }

    public void setProyectonombre(String proyectonombre) {
        this.proyectonombre = proyectonombre;
    }

    public String getUsuariocorreo() {
        return usuariocorreo;
    }

    public void setUsuariocorreo(String usuariocorreo) {
        this.usuariocorreo = usuariocorreo;
    }
}
