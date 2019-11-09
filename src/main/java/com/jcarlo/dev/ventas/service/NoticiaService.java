package com.jcarlo.dev.ventas.service;

import com.jcarlo.dev.ventas.model.Noticia;
import com.jcarlo.dev.ventas.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticias")
public class NoticiaService {

    @Autowired
    NoticiaRepository noticiaRepository;

    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Noticia> getAll() {
        return noticiaRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public List<Noticia> deleteNoticia(@PathVariable("id") int id) {
        noticiaRepository.deleteById(id);
        return noticiaRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Noticia createNoticia(@RequestBody Noticia noticia) {
        return noticiaRepository.save(noticia);
    }
}
