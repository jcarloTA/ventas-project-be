package com.jcarlo.dev.ventas.service;

import com.jcarlo.dev.ventas.model.Usuario;
import com.jcarlo.dev.ventas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public void deleteUsuario(@PathVariable("id") int id) {
        usuarioRepository.deleteById(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Usuario createUsuario(@RequestBody Usuario noticia) {
        return usuarioRepository.save(noticia);
    }


}
