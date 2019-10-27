package com.jcarlo.dev.ventas.repository;

import com.jcarlo.dev.ventas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ProductoRepository extends JpaRepository<Producto, Serializable> {
}
