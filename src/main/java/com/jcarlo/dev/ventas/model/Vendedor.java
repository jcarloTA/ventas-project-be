package com.jcarlo.dev.ventas.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Vendedor implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;
    @Column
    private String apellido;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column
    private String direccion;
    @Column
    private String contrasena;
    @Column
    private String estado;

    @OneToMany(cascade=CascadeType.ALL,fetch= FetchType.LAZY,mappedBy="idVendedor")
    private List<Telefono> telefonos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
