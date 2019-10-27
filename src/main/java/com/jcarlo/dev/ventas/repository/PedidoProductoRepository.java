package com.jcarlo.dev.ventas.repository;

import com.jcarlo.dev.ventas.model.PedidoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, Serializable> {
}
