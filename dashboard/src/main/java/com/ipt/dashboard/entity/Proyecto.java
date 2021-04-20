package com.ipt.dashboard.entity;


import javax.persistence.*;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idproyecto")
    private int proyectoid;
    @Column(name="nombreProyecto")
    private String proyectonombre;
    @Column(name="usuario_owner")
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
