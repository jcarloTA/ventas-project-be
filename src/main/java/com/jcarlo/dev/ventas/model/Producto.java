package com.jcarlo.dev.ventas.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private String imagen;
    @Column
    private int costo;

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId() {
        return id;
    }

    public int getCosto() {
        return costo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
