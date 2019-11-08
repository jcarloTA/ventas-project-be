package com.jcarlo.dev.ventas.repository;


import com.jcarlo.dev.ventas.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface VendedorRepository extends JpaRepository<Vendedor, Serializable> {

    List<Vendedor> findByNombreAndContrasena(String nombre, String contrasena);

}
