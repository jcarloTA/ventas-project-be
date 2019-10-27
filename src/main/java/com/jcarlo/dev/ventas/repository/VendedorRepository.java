package com.jcarlo.dev.ventas.repository;

import com.jcarlo.dev.ventas.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface VendedorRepository extends JpaRepository<Vendedor, Serializable> {

}
