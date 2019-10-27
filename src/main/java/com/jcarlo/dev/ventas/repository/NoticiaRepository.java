package com.jcarlo.dev.ventas.repository;

import com.jcarlo.dev.ventas.model.Noticia;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticiaRepository extends JpaRepository<Noticia, Serializable>{

}
