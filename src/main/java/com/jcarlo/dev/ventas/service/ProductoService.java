package com.jcarlo.dev.ventas.service;

import com.jcarlo.dev.ventas.model.Noticia;
import com.jcarlo.dev.ventas.model.Producto;
import com.jcarlo.dev.ventas.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }


    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public void deleteProducto(@PathVariable("id") int id) {
        productoRepository.deleteById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

}
