package com.jcarlo.dev.ventas.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Pedido {


    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;
    @Column
    private int montoTotal;

    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;

    @Column
    private int idVendedor;

    @OneToMany(cascade=CascadeType.ALL,fetch= FetchType.LAZY,mappedBy="idPedido")
    private List<PedidoProducto> detalles;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<PedidoProducto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<PedidoProducto> detalles) {
        this.detalles = detalles;
    }
}
