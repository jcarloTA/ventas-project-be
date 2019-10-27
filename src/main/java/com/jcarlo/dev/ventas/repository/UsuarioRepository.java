package com.jcarlo.dev.ventas.repository;


import com.jcarlo.dev.ventas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UsuarioRepository extends JpaRepository<Usuario, Serializable> {
}
