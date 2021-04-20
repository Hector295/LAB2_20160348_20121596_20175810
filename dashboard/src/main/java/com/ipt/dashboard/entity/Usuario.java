package com.ipt.dashboard.entity;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuario{
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getIdarea() {
        return idarea;
    }

    public void setIdarea(int idarea) {
        this.idarea = idarea;
    }

    @Id
    @Column(nullable = false)
    private String correo;
    private String nombres;
    private String apellidos;
    @Column(nullable = false)
    private int idarea;
}
