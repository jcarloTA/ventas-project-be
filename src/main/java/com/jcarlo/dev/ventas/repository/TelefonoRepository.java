package com.jcarlo.dev.ventas.repository;

import com.jcarlo.dev.ventas.model.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface TelefonoRepository extends JpaRepository<Telefono, Serializable> {
}
